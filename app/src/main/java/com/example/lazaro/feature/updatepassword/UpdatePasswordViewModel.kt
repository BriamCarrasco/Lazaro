package com.example.lazaro.feature.updatepassword

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * ViewModel encargado de gestionar la lógica de actualización de contraseña del usuario.
 *
 * Expone el estado de la UI mediante un StateFlow y valida los campos antes de interactuar con Firebase.
 * Realiza la reautenticación del usuario y actualiza la contraseña, notificando el resultado a la UI.
 */
class UpdatePasswordViewModel : ViewModel(){

    private val _uiState = MutableStateFlow<PasswordUpdateState>(PasswordUpdateState.Idle)
    val uiState: StateFlow<PasswordUpdateState> = _uiState

    fun updatePassword(currentPassword: String, newPassword: String, confirmNewPassword: String) {
        if (currentPassword.isBlank() || newPassword.isBlank() || confirmNewPassword.isBlank()) {
            _uiState.value = PasswordUpdateState.Error("Ningún campo puede estar vacío")
            return
        }
        if (newPassword != confirmNewPassword) {
            _uiState.value = PasswordUpdateState.Error("Las contraseñas no coinciden")
            return
        }
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email
        if (user == null || email == null) {
            _uiState.value = PasswordUpdateState.Error("Usuario no autenticado")
            return
        }
        _uiState.value = PasswordUpdateState.Loading
        val credential = EmailAuthProvider.getCredential(email, currentPassword)
        user.reauthenticate(credential)
            .addOnSuccessListener {
                user.updatePassword(newPassword)
                    .addOnSuccessListener {
                        _uiState.value = PasswordUpdateState.Success
                    }
                    .addOnFailureListener { e ->
                        _uiState.value = PasswordUpdateState.Error(e.message ?: "Error al actualizar contraseña")
                    }
            }
            .addOnFailureListener {
                _uiState.value = PasswordUpdateState.Error("Contraseña actual incorrecta")
            }
    }
}


/**
 * Representa los diferentes estados posibles durante el proceso de actualización de contraseña.
 */
sealed class PasswordUpdateState {
    /** Estado inicial o inactivo. */
    object Idle : PasswordUpdateState()

    /** Estado de carga mientras se procesa la actualización. */
    object Loading : PasswordUpdateState()

    /** Estado de éxito cuando la contraseña se actualiza correctamente. */
    object Success : PasswordUpdateState()

    /**
     * Estado de error con un mensaje descriptivo.
     * @param message Mensaje de error a mostrar.
     */
    data class Error(val message: String) : PasswordUpdateState()
}