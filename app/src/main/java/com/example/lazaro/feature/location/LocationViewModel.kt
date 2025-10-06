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

class LocationViewModel(application: Application) : AndroidViewModel(application) {
    private val _location = MutableStateFlow<Location?>(null)
    val location: StateFlow<Location?> = _location

    private val _address = MutableStateFlow<String?>(null)
    val address: StateFlow<String?> = _address

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