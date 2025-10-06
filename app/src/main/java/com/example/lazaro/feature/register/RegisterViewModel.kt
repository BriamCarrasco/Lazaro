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

class RegisterViewModel(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : ViewModel() {
    var id by mutableStateOf(0)
    var nombreUsuario by mutableStateOf("")
    var nombre by mutableStateOf("")
    var apellidoP by mutableStateOf("")
    var ApellidoM by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")



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