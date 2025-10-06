package com.example.lazaro.feature.register

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

/**
 * Pantalla de registro principal que gestiona el flujo entre los pasos del registro.
 *
 * Controla la navegación entre los pasos del formulario de registro utilizando un estado interno.
 * Muestra mensajes de validación y permite regresar a pantallas anteriores mediante el `NavHostController`.
 *
 * @param navRouter Controlador de navegación para gestionar el flujo de pantallas.
 * @param viewModel Instancia de [RegisterViewModel] para manejar el estado y validación de los datos del registro.
 */
@Composable
fun registerScreen(navRouter: NavHostController, viewModel: RegisterViewModel) {
    var step by remember { mutableStateOf(1) }
    val context = LocalContext.current

    when (step) {
        1 -> RegisterScreenStep1(
            viewModel = viewModel,
            onNext = {
                if (viewModel.isStep1Valid()) {
                    step = 2
                } else {
                    Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            },
            onBack = {
                navRouter.popBackStack()
            }
        )
        2 -> RegisterScreenStep2(
            viewModel = viewModel,
            onRegister = {
                navRouter.navigate("loginScreen") {
                    popUpTo("registerS") { inclusive = true }
                }
            },
            onBack = {
                navRouter.popBackStack()
            }
        )
    }
}