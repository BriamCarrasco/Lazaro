package com.example.lazaro.nav



/**
 * Clase sellada que define las rutas de navegación de la aplicación.
 *
 * Cada objeto representa una pantalla específica y su ruta asociada.
 *
 * @property route Cadena que identifica la ruta de la pantalla.
 */
sealed class NavRouter(val route: String) {
    /** Ruta para la pantalla de inicio de sesión. */
    object LoginScreen : NavRouter("loginScreen")
    /** Ruta para la pantalla de registro de usuario. */
    object RegisterScreen : NavRouter("registerScreen")
    /** Ruta para la pantalla principal (home). */
    object HomeScreen : NavRouter("homeScreen")
    /** Ruta para la pantalla de recuperación de contraseña. */
    object RecoverPassScreen : NavRouter("recoverPassScreen")
    /** Ruta para la pantalla de edición de perfil. */
    object EditProfile : NavRouter("editProfile")
    /** Ruta para la pantalla de actualización de contraseña. */
    object UpdatePasswordScreen : NavRouter("updatePasswordScreen")
    /** Ruta para la pantalla de configuración. */
    object SettingsScreen : NavRouter("settingsScreen")
    /** Ruta para la pantalla de ubicación. */
    object LocationScreen : NavRouter("locationScreen")
    /** Ruta para la pantalla de reconocimiento de voz (STT). */
    object STTScreen : NavRouter("sttScreen")
    /** Ruta para la pantalla de conversión de texto a voz (TTS). */
    object TTSScreen : NavRouter("ttsScreen")

}