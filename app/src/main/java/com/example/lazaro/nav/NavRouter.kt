package com.example.lazaro.nav

sealed class NavRouter (val route: String) {
    object LoginScreen : NavRouter("loginScreen")
    object RegisterScreen: NavRouter("registerScreen")
    object Home: NavRouter("homeScreen")
    object RecoverPassword: NavRouter("recoverPassScreen")

}