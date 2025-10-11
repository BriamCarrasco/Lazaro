package com.example.lazaro.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.lazaro.R
import androidx.compose.foundation.layout.Column
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.camera.core.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.camera.view.PreviewView
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.core.CameraSelector
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.example.lazaro.topbarhome.TopBarHome
import com.example.lazaro.feature.reusable.drawerHome
import kotlinx.coroutines.launch
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lazaro.feature.session.SessionViewModel
import com.example.lazaro.data.UsersRoomRepository
import com.example.lazaro.data.AppDatabase
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.graphicsLayer



/**
 * Pantalla principal de la aplicación.
 *
 * Muestra accesos rápidos a funcionalidades como escaneo de billetes, ubicación, lectura y escucha de texto.
 * Gestiona permisos de cámara, sesión de usuario y permite alternar el tema oscuro.
 *
 * @param navRouter Controlador de navegación para gestionar el flujo de pantallas.
 * @param darkThemeEnabled Indica si el tema oscuro está activado.
 * @param onToggleTheme Acción para alternar entre tema claro y oscuro.
 */
@Composable
fun homeScreen(navRouter: NavHostController, darkThemeEnabled: Boolean, onToggleTheme: () -> Unit) {

    val sessionViewModel: SessionViewModel = viewModel()
    val context = LocalContext.current
    var showCamera by remember { mutableStateOf(false) }
    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    BackHandler {
        //Se deja vacio para deshabilitar el boton de retroceso del dispositivo
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasPermission = granted
    }


    LaunchedEffect(Unit) {
        if (!hasPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }

        val username = sessionViewModel.loadSession(context)
        if (username != null) {
            val db = AppDatabase.getInstance(context)
            val repository = UsersRoomRepository(db.usersDao())
            val userEntity = repository.getUserByUsername(username)
            if (userEntity != null) {
                sessionViewModel.login(userEntity)
            }
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
        }
    }

    if(showCamera){
        Box(modifier = Modifier.fillMaxSize()) {
            CameraXPreview(context)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0f))
                    .padding(8.dp)
                .statusBarsPadding(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { showCamera = false }) {
                    Image(
                        painter = painterResource(id = R.drawable.cerrar),
                        contentDescription = "Cerrar cámara",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(Color(0xFFFD9C00))
                   )
                }
            }
        }
    }else {

        drawerHome(
            topBar = { drawerState, scope ->
                TopBarHome(
                    title = "Lazaro",
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .imePadding()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 32.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .height(200.dp)
                            .clickable {
                                showCamera = true
                            },
                            colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.dinero),
                                contentDescription = "Escanear billete",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .align(Alignment.Center),
                                alpha = 0.1f
                            )
                            Text(
                                text = "Escanear billete",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .clickable{
                                navRouter.navigate("locationScreen"){
                                    launchSingleTop = true
                                }
                            }
                            .height(200.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mapa),
                                contentDescription = "Mi ubicación",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .align(Alignment.Center),
                                alpha = 0.1f
                            )
                            Text(
                                text = "Mi ubicación",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        Card(
                            modifier = Modifier
                                .size(200.dp)
                                .weight(1f)
                                .clickable { navRouter.navigate("ttsScreen"){
                                    launchSingleTop = true
                                }
                                }
                                .padding(vertical = 16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    painter = painterResource(id = R.drawable.hablando),
                                    contentDescription = "Leer",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .align(Alignment.Center),
                                    alpha = 0.1f
                                )
                                Text(
                                    text = "Leer",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Card(
                            modifier = Modifier
                                .size(200.dp)
                                .weight(1f)
                                .clickable { navRouter.navigate("sttScreen"){launchSingleTop = true} }
                                .padding(vertical = 16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    painter = painterResource(id = R.drawable.microfono),
                                    contentDescription = "Escuchar",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .align(Alignment.Center),
                                    alpha = 0.1f
                                )
                                Text(
                                    text = "Escuchar",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }


                }
            },
            darkThemeEnabled = darkThemeEnabled,
            onToggleTheme = onToggleTheme,
            sessionViewModel = sessionViewModel,
            navController = navRouter
        )
    }
}

@Composable
fun CameraXPreview(context: Context) {
    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
            }
            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder()
                    //.setTargetAspectRatio(AspectRatio.RATIO_4_3)
                    .build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        ctx as androidx.lifecycle.LifecycleOwner,
                        cameraSelector,
                        preview
                    )
                } catch (exc: Exception) {
                    Log.e("CameraX", "Error: ${exc.message}")
                }
            }, ContextCompat.getMainExecutor(ctx))
            previewView
        },
        modifier = Modifier
            .fillMaxSize()
    )
}