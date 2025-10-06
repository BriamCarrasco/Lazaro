package com.example.lazaro.feature.register

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lazaro.data.Users
import com.example.lazaro.data.UsersRoomRepository
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.text.set


/**
 * ViewModel encargado de gestionar el registro de usuarios.
 *
 * Maneja el estado de los campos del formulario de registro y la lógica de validación.
 * Permite registrar usuarios en Firebase Authentication y guardar sus datos en Firestore.
 *
 * @property auth Instancia de [FirebaseAuth] para autenticación de usuarios.
 * @property db Instancia de [FirebaseFirestore] para almacenar datos de usuario.
 */
class RegisterViewModel(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : ViewModel() {

    /** Nombre de usuario. */
    var nombreUsuario by mutableStateOf("")

    /** Nombre del usuario. */
    var nombre by mutableStateOf("")

    /** Apellido paterno del usuario. */
    var apellidoP by mutableStateOf("")

    /** Apellido materno del usuario. */
    var ApellidoM by mutableStateOf("")

    /** Correo electrónico del usuario. */
    var email by mutableStateOf("")

    /** Contraseña del usuario. */
    var password by mutableStateOf("")


    /**
     * Valida que los campos del primer paso no estén vacíos.
     * @return `true` si todos los campos están completos, `false` en caso contrario.
     */
    fun isStep1Valid(): Boolean {
        return nombre.isNotBlank() &&
                apellidoP.isNotBlank() &&
                ApellidoM.isNotBlank()
    }


    /**
     * Valida que los campos del segundo paso no estén vacíos.
     * @return `true` si todos los campos están completos, `false` en caso contrario.
     */
    fun isStep2Valid(): Boolean {
        return nombreUsuario.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank()
    }



    /**
     * Registra un nuevo usuario en Firebase Authentication y almacena sus datos en Firestore.
     *
     * @param onResult Callback que indica si el registro fue exitoso y un mensaje de error opcional.
     */
    fun registerUserFireBase(onResult: (Boolean, String?) -> Unit) {
        if (!isStep2Valid()) {
            onResult(false, "Campos vacíos")
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: ""
                    val userMap = hashMapOf(
                        "nombreUsuario" to nombreUsuario,
                        "nombre" to nombre,
                        "apellidoP" to apellidoP,
                        "apellidoM" to ApellidoM,
                        "email" to email
                    )
                    db.collection("users").document(userId)
                        .set(userMap)
                        .addOnSuccessListener { onResult(true, null) }
                        .addOnFailureListener { e -> onResult(false, e.message) }
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }



}