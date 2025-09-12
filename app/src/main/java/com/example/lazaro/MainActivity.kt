package com.example.lazaro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
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
import com.example.lazaro.feature.login.LoginViewModel
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.lazaro.feature.session.SessionViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var darkThemeEnabled by remember { mutableStateOf(false) }
            LazaroTheme (darkThemeEnabled){
                val navController = rememberNavController()
                val sessionViewModel: SessionViewModel = viewModel()
                val startDestination = if (sessionViewModel.currentUser.value != null) {
                    NavRouter.HomeScreen.route
                } else {
                    NavRouter.LoginScreen.route
                }
                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ){
                    composable(NavRouter.LoginScreen.route){
                        val loginViewModel: LoginViewModel = viewModel()
                        loginScreen(navController, loginViewModel, sessionViewModel)

                    }
                    composable (NavRouter.RegisterScreen.route){
                        val registerViewModel: RegisterViewModel = viewModel()
                        registerScreen(navController, registerViewModel)
                    }
                    composable (NavRouter.HomeScreen.route){
                        homeScreen(navController,
                            darkThemeEnabled = darkThemeEnabled,
                            onToggleTheme = { darkThemeEnabled = !darkThemeEnabled })
                    }
                    composable (NavRouter.RecoverPassScreen.route) {
                        recoverPassScreen(navController, onBack = {
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }
}


