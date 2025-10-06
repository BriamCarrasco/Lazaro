package com.example.lazaro.feature.recoverypass

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * ViewModel encargado de gestionar la recuperación de contraseña.
 *
 * Utiliza Firebase Authentication para enviar correos de restablecimiento de contraseña
 * y expone el estado de la operación mediante un flujo observable.
 *
 * @property firebaseAuth Instancia de [FirebaseAuth] para interactuar con la autenticación de Firebase.
 */
sealed class RecoverPassState {
    object Idle : RecoverPassState()
    object Success : RecoverPassState()
    data class Error(val message: String) : RecoverPassState()
    object Loading : RecoverPassState()
}

class RecoverViewModel(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) : ViewModel() {

    /**
     * Estado interno de la recuperación de contraseña.
     */
    private val _state = MutableStateFlow<RecoverPassState>(RecoverPassState.Idle)

    /**
     * Estado observable de la recuperación de contraseña.
     */
    val state: StateFlow<RecoverPassState> = _state


    /**
     * Envía un correo de restablecimiento de contraseña al usuario.
     *
     * @param email Correo electrónico del usuario que solicita el restablecimiento.
     */
    fun sendPasswordReset(email: String) {
        _state.value = RecoverPassState.Loading
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                _state.value = if (task.isSuccessful) {
                    RecoverPassState.Success
                } else {
                    RecoverPassState.Error(task.exception?.message ?: "Error desconocido")
                }
            }
    }
}