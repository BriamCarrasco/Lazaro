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
import androidx.compose.runtime.getValue
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.navigation.NavHostController
import com.example.lazaro.feature.session.SessionViewModel
import androidx.compose.ui.platform.LocalContext


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

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (
                modifier = Modifier.width(280.dp)
            ){

                val currentUser = sessionViewModel.currentUser.value

                Log.d("Drawer", "Current user: $currentUser")
                Text(
                    text = if (currentUser != null) {
                        "Bienvenido, ${currentUser.nombre} ${currentUser.apellidoP}"
                    } else {
                        "Bienvenido"
                    },
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.primary,
                )


                ThemeSwitcherLottie(
                    darkThemeEnabled = darkThemeEnabled,
                    onToggleTheme = onToggleTheme
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        navController.navigate("editProfile")
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ){
                    Text("Editar perfil")
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        sessionViewModel.logout()
                        sessionViewModel.saveSession(context, null)
                        navController.navigate("loginScreen") {
                            popUpTo("homeScreen") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Cerrar sesión")
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


