package com.example.lazaro.feature.login

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazaro.data.Users
import com.example.lazaro.data.UsersRoomRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UsersRoomRepository) : ViewModel() {

    var userName by mutableStateOf("")
    var password by mutableStateOf("")
    var usuarioAutenticado by mutableStateOf<Users?>(null)

    private val _loginEvent = Channel<LoginState>()
    val loginEvent = _loginEvent.receiveAsFlow()

    var loginState by mutableStateOf<LoginState>(LoginState.Idle)
        private set

    fun login() {
        viewModelScope.launch {
            val user = repository.login(userName, password)
            if (user != null) {
                usuarioAutenticado = user
                _loginEvent.send(LoginState.Success)
            } else {
                _loginEvent.send(LoginState.Error)
            }
        }
    }


}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    object Error : LoginState()
}