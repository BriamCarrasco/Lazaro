package com.example.lazaro.feature.reusable

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import kotlinx.coroutines.CoroutineScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.navigation.NavHostController
import com.example.lazaro.feature.session.SessionViewModel
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Row
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow



/**
 * Drawer lateral reutilizable para la pantalla principal de la aplicación.
 *
 * Muestra información del usuario, permite cambiar el tema, navegar a otras pantallas
 * y cerrar sesión. Utiliza Firestore para obtener los datos del usuario autenticado.
 *
 * @param topBar Composable que representa la barra superior, recibe el estado del drawer y el scope.
 * @param content Contenido principal que se muestra junto al drawer.
 * @param darkThemeEnabled Indica si el tema oscuro está activado.
 * @param onToggleTheme Acción para alternar entre tema claro y oscuro.
 * @param sessionViewModel ViewModel de sesión para gestionar el estado del usuario.
 * @param navController Controlador de navegación para gestionar el flujo entre pantallas.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun drawerHome(
    topBar: @Composable (drawerState: DrawerState, scope: CoroutineScope) -> Unit,
    content: @Composable (PaddingValues) -> Unit = {},
    darkThemeEnabled: Boolean,
    onToggleTheme: () -> Unit,
    sessionViewModel: SessionViewModel,
    navController: NavHostController

) {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var nombre by remember { mutableStateOf<String?>(null) }
    var apellidoP by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        user?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("users").document(uid)
                .get()
                .addOnSuccessListener { doc ->
                    nombre = doc.getString("nombre")
                    apellidoP = doc.getString("apellidoP")
                }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (
                modifier = Modifier.widthIn(min = 200.dp, max = 450.dp)
            ){

                Text(
                    text = if (nombre != null && apellidoP != null) {
                        "Bienvenido: $nombre $apellidoP"
                    } else {
                        "Bienvenido"
                    },
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ){
                    Crossfade(targetState = darkThemeEnabled, label = "themeText") { dark ->
                        val text = if (dark) "Desactivar tema oscuro" else "Activar tema oscuro"
                        Text(
                            text = text,
                            modifier = Modifier.padding(end = 8.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    ThemeSwitcherLottie(
                        darkThemeEnabled = darkThemeEnabled,
                        onToggleTheme = onToggleTheme,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate("editProfile")
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ){
                    Text("Editar perfil",
                        style = MaterialTheme.typography.bodyLarge)
                }

                Button(
                    onClick = {
                        navController.navigate("settingsScreen")
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ){
                    Text("Configuraciones",
                        style = MaterialTheme.typography.bodyLarge)
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        //sessionViewModel.logout()
                        //sessionViewModel.saveSession(context, null)
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate("loginScreen") {
                            popUpTo("homeScreen") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(32.dp)
                ) {
                    Text("Cerrar sesión",
                        style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    ) {

        Scaffold(
            topBar = { topBar(drawerState, scope) }
        ) { innerPadding ->
            content(innerPadding)
        }

    }
}


