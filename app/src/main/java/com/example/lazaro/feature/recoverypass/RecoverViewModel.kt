package com.example.lazaro.feature.recoverypass

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class RecoverPassState {
    object Idle : RecoverPassState()
    object Success : RecoverPassState()
    data class Error(val message: String) : RecoverPassState()
    object Loading : RecoverPassState()
}

class RecoverViewModel(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) : ViewModel() {

    private val _state = MutableStateFlow<RecoverPassState>(RecoverPassState.Idle)
    val state: StateFlow<RecoverPassState> = _state

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