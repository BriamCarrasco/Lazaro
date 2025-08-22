package com.example.lazaro.feature.register

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lazaro.data.Users
import com.example.lazaro.data.UsersRepository

class RegisterViewModel : ViewModel() {
    var id by mutableStateOf(0)
    var nombreUsuario by mutableStateOf("")
    var nombre by mutableStateOf("")
    var apellidoP by mutableStateOf("")
    var ApellidoM by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    fun registerUser() {
        val newUser = Users(
            id = id,
            nombreUsuario = nombreUsuario,
            nombre = nombre,
            apellidoP = apellidoP,
            ApellidoM = ApellidoM,
            email = email,
            password = password
        )
        UsersRepository.addUsers(newUser)
    }

    fun isStep1Valid(): Boolean {
        return nombre.isNotBlank() &&
                apellidoP.isNotBlank() &&
                ApellidoM.isNotBlank()
    }

    fun isStep2Valid(): Boolean {
        return nombreUsuario.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank()
    }
}