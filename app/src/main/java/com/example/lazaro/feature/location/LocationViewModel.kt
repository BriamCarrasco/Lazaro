package com.example.lazaro.feature.location

import android.app.Application
import android.content.Context
import android.location.Geocoder
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale


/**
 * ViewModel encargado de gestionar la obtención de la ubicación y dirección actual del usuario.
 *
 * Utiliza los servicios de ubicación de Google y Geocoder para obtener la localización y traducirla a una dirección legible.
 * Expone el estado de la ubicación y la dirección mediante flujos observables.
 *
 * @constructor Recibe la aplicación para acceder a recursos y contexto.
 */
class LocationViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Estado observable que contiene la última ubicación obtenida.
     */
    private val _location = MutableStateFlow<Location?>(null)

    val location: StateFlow<Location?> = _location


    /**
     * Estado observable que contiene la dirección correspondiente a la ubicación.
     */
    private val _address = MutableStateFlow<String?>(null)
    val address: StateFlow<String?> = _address


    /**
     * Obtiene la ubicación actual del usuario y actualiza el estado.
     *
     * @param context Contexto necesario para acceder a los servicios de ubicación.
     */
    fun getLocation(context: Context) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        val cancellationTokenSource = CancellationTokenSource()
        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            cancellationTokenSource.token
        ).addOnSuccessListener { loc ->
            _location.value = loc
            getAddress(context, loc)
        }
    }


    /**
     * Obtiene la dirección legible a partir de una ubicación y actualiza el estado.
     *
     * @param context Contexto necesario para el Geocoder.
     * @param loc Ubicación a traducir en dirección.
     */
    private fun getAddress(context: Context, loc: Location?) {
        viewModelScope.launch {
            if (loc != null) {
                try {
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val addresses = geocoder.getFromLocation(loc.latitude, loc.longitude, 1)
                    _address.value = if (!addresses.isNullOrEmpty()) {
                        val addr = addresses[0]
                        listOfNotNull(
                            addr.thoroughfare,
                            addr.locality,
                            addr.adminArea,
                            addr.countryName
                        ).joinToString(", ")
                    } else {
                        "No disponible"
                    }
                } catch (e: Exception) {
                    _address.value = "Error obteniendo dirección"
                }
            } else {
                _address.value = null
            }
        }
    }
}