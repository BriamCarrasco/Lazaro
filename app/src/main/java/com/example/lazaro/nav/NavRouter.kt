package com.example.lazaro.nav

sealed class NavRouter (val route: String) {
    object LoginScreen : NavRouter("loginScreen")
    object RegisterScreen: NavRouter("registerScreen")
    object HomeScreen: NavRouter("homeScreen")
    object RecoverPassScreen: NavRouter("recoverPassScreen")
    object EditProfile: NavRouter("editProfile")

}