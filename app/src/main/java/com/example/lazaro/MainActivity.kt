package com.example.lazaro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.lazaro.nav.NavRouter
import com.example.lazaro.ui.theme.LazaroTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lazaro.feature.login.loginScreen
import com.example.lazaro.feature.register.RegisterViewModel
import com.example.lazaro.feature.register.registerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazaroTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = NavRouter.LoginScreen.route
                ){
                    composable(NavRouter.LoginScreen.route){
                        loginScreen(navController)

                    }
                    composable (NavRouter.RegisterScreen.route){
                        val registerViewModel: RegisterViewModel = viewModel()
                        registerScreen(navController, registerViewModel)
                    }
                }
            }
        }
    }
}



// TODO: GENERAR PANTALLA LOGIN
// TODO: GENERAR PANTALLA REGISTRO
// TODO: GENERAR PANTALLA HOME
// TODO: GENERAR ARRAY PARA USUARIOS (GUARDAR AL MENOS 5 USUARIOS).
// TODO: GENERAR PANTALLA PARA RECUPERAR CONTRASEÑA
