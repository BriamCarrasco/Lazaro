package com.example.lazaro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lazaro.ui.theme.LazaroTheme
import com.example.lazaro.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazaroTheme {
                LoginScreen().loginScreen()
            }
        }
    }
}



// TODO: GENERAR PANTALLA LOGIN
// TODO: GENERAR PANTALLA REGISTRO
// TODO: GENERAR PANTALLA HOME
// TODO: GENERAR ARRAY PARA USUARIOS (GUARDAR AL MENOS 5 USUARIOS).
// TODO: GENERAR PANTALLA PARA RECUPERAR CONTRASEÑA
