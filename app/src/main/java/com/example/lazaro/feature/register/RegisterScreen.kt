package com.example.lazaro.feature.register

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController


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
                if (viewModel.isStep2Valid()) {
                    viewModel.registerUser()
                    Toast.makeText(context, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                }
            },
            onBack = {
                navRouter.popBackStack()
            }
        )
    }
}