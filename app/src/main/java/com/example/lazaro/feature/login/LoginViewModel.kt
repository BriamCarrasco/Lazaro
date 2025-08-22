package com.example.lazaro.feature.login

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lazaro.data.UsersRepository

class LoginViewModel : ViewModel() {

    init {
        UsersRepository.usersPrecargados()
    }
    var userName by mutableStateOf("")
    var password by mutableStateOf("")
    var loginCorrecto by mutableStateOf(false)

    fun login(){

        val userFind = UsersRepository.getUsers().find{
            it.nombreUsuario == userName && it.password == password

        }
        loginCorrecto = userFind != null




    }

}