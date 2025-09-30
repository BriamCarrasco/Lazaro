package com.example.lazaro.feature.editprofile

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.text.get

data class UserProfile(
    val nombre: String = "",
    val apellidoP: String = "",
    val apellidoM: String = "",
    val nombreUsuario: String = "",
    val email: String = ""
)

class EditProfileViewModel : ViewModel() {
    private val _profile = MutableStateFlow(UserProfile())
    val profile: StateFlow<UserProfile> = _profile
    private val _originalProfile = MutableStateFlow<UserProfile?>(null)

    val originalProfile: StateFlow<UserProfile?> = _originalProfile

    fun loadUserProfile() {
        val user = FirebaseAuth.getInstance().currentUser ?: return
        FirebaseFirestore.getInstance().collection("users").document(user.uid)
            .get()
            .addOnSuccessListener { doc ->
                val loadedProfile = UserProfile(
                    nombre = doc.getString("nombre") ?: "",
                    apellidoP = doc.getString("apellidoP") ?: "",
                    apellidoM = doc.getString("apellidoM") ?: "",
                    nombreUsuario = doc.getString("nombreUsuario") ?: "",
                    email = doc.getString("email") ?: ""
                )
                _profile.value = loadedProfile
                _originalProfile.value = loadedProfile // Guarda el original
            }
    }

    fun updateField(field: String, value: String) {
        _profile.value = when (field) {
            "nombre" -> _profile.value.copy(nombre = value)
            "apellidoP" -> _profile.value.copy(apellidoP = value)
            "apellidoM" -> _profile.value.copy(apellidoM = value)
            "nombreUsuario" -> _profile.value.copy(nombreUsuario = value)
            "email" -> _profile.value.copy(email = value)
            else -> _profile.value
        }
    }

    fun updateUserProfile(onResult: (Boolean) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser ?: return
        val data = mapOf(
            "nombre" to _profile.value.nombre,
            "apellidoP" to _profile.value.apellidoP,
            "apellidoM" to _profile.value.apellidoM,
            "nombreUsuario" to _profile.value.nombreUsuario,
            "email" to _profile.value.email
        )
        FirebaseFirestore.getInstance().collection("users").document(user.uid)
            .update(data)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

}
