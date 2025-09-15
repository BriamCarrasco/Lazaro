package com.example.lazaro.feature.login

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazaro.data.Users
import com.example.lazaro.data.UsersRoomRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UsersRoomRepository) : ViewModel() {


    //init { UsersRepository.usersPrecargados() }

    var userName by mutableStateOf("")
    var password by mutableStateOf("")
    var loginCorrecto by mutableStateOf(false)
    var usuarioAutenticado  by mutableStateOf<Users?>(null)

    fun login() {
        viewModelScope.launch {
            val user = repository.login(userName, password)
            loginCorrecto = user != null
            usuarioAutenticado = user
        }
    }

}