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
import com.example.lazaro.feature.home.homeScreen
import com.example.lazaro.feature.login.loginScreen
import com.example.lazaro.feature.recoverypass.recoverPassScreen
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
                    composable (NavRouter.HomeScreen.route){
                        homeScreen(navController)
                    }
                    composable (NavRouter.RecoverPassScreen.route) {
                        recoverPassScreen(navController)
                    }
                }
            }
        }
    }
}



// TODO: GENERAR PANTALLA LOGIN --> HECHO
// TODO: GENERAR PANTALLA REGISTRO --> HECHO
// TODO: GENERAR PANTALLA HOME --> EN PROCESO
// TODO: GENERAR ARRAY PARA USUARIOS (GUARDAR AL MENOS 5 USUARIOS). --> EN PROCESO
// TODO: GENERAR PANTALLA PARA RECUPERAR CONTRASEÑA --> EN PROCESO
