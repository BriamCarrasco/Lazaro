package com.example.lazaro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lazaro.ui.theme.LazaroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazaroTheme {
            }
        }
    }
}



// TODO: GENERAR PANTALLA LOGIN
// TODO: GENERAR PANTALLA REGISTRO
// TODO: GENERAR PANTALLA HOME
// TODO: GENERAR ARRAY PARA USUARIOS (GUARDAR AL MENOS 5 USUARIOS).
// TODO: GENERAR PANTALLA PARA RECUPERAR CONTRASEÑA
