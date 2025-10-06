package com.example.lazaro.feature.location

import android.Manifest
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lazaro.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import topBarBack
import java.util.Locale
import kotlin.collections.get
import kotlin.toString

@Composable
fun LocationScreen(navRouter: NavController, onBack: () -> Unit, viewModel: LocationViewModel = viewModel()) {
    val context = LocalContext.current
    val location by viewModel.location.collectAsState()
    val address by viewModel.address.collectAsState()
    var hasLocationPermission by remember { mutableStateOf(false) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            hasLocationPermission = isGranted
            if (isGranted) {
                viewModel.getLocation(context)
            } else {
                Toast.makeText(context, "Permiso de ubicación denegado.", Toast.LENGTH_SHORT).show()
            }
        }
    )

    LaunchedEffect(Unit) {
        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    fun compartirUbicacion() {
        val loc = location
        val texto = if (loc != null) {
            val mapsUrl = "https://maps.google.com/?q=${loc.latitude},${loc.longitude}"
            "Estoy aquí: ${address ?: ""}\n$mapsUrl"
        } else {
            "Ubicación no disponible"
        }
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, texto)
        }
        context.startActivity(Intent.createChooser(intent, "Compartir ubicación"))
    }

    Scaffold(
        topBar = {
            topBarBack(
                onBackClick = onBack,
                title = ""
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Mi ubicación actual",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            if (hasLocationPermission) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(200.dp)
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.ubicacioahora),
                            contentDescription = "Escanear billete",
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center),
                            alpha = 0.1f
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("Latitud: ${location?.latitude ?: "N/A"}", style = MaterialTheme.typography.bodyLarge)
                            Text("Longitud: ${location?.longitude ?: "N/A"}", style = MaterialTheme.typography.bodyLarge)
                            Text("Dirección: ${address ?: "Obteniendo dirección..."}", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.getLocation(context) }) {
                    Text("Actualizar Ubicación")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { compartirUbicacion() }, enabled = location != null) {
                    Text("Compartir ubicación")
                }
            } else {
                Button(onClick = {
                    locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }) {
                    Text("Solicitar Permiso de Ubicación")
                }
            }
        }
    }
}
