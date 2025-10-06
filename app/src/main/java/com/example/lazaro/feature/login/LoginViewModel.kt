package com.example.lazaro.feature.login

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazaro.data.Users
import com.example.lazaro.data.UsersRoomRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


/**
 * ViewModel encargado de gestionar el proceso de inicio de sesión.
 *
 * Utiliza Firebase Authentication para autenticar usuarios mediante correo electrónico y contraseña.
 * Expone el estado del inicio de sesión a través de flujos observables y valida los campos del formulario.
 *
 * @property firebaseAuth Instancia de [FirebaseAuth] para interactuar con la autenticación de Firebase.
 * @property email Correo electrónico ingresado por el usuario.
 * @property password Contraseña ingresada por el usuario.
 * @property loginEvent Canal para emitir eventos de estado de inicio de sesión.
 * @property loginState Estado observable del proceso de inicio de sesión.
 */
class LoginViewModel(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) : ViewModel() {

    /**
     * Correo electrónico ingresado por el usuario.
     */
    var email by mutableStateOf("")

    /**
     * Contraseña ingresada por el usuario.
     */
    var password by mutableStateOf("")

    private val _loginEvent = Channel<LoginState>()
    val loginEvent = _loginEvent.receiveAsFlow()


    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState = _loginState.asStateFlow()

    /**
     * Verifica si los campos de correo y contraseña no están vacíos.
     *
     * @return `true` si ambos campos son válidos, `false` en caso contrario.
     */
    fun isValid(): Boolean {
        return email.isNotBlank() &&
                password.isNotBlank()
    }


    /**
     * Realiza el inicio de sesión utilizando Firebase Authentication.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     */
    fun loginWithFirebase(email: String, password: String) {
        _loginState.value = LoginState.Loading
        firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _loginState.value = if (task.isSuccessful) {
                    LoginState.Success
                } else {
                    LoginState.Error
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