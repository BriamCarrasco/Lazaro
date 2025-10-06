package com.example.lazaro.feature.editprofile

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.text.get


/**
 * ViewModel encargado de gestionar la edición y actualización del perfil de usuario.
 *
 * Utiliza Firebase Authentication y Firestore para cargar y actualizar los datos del usuario.
 * Expone el estado del perfil editable y el perfil original mediante flujos observables.
 */
data class UserProfile(
    val nombre: String = "",
    val apellidoP: String = "",
    val apellidoM: String = "",
    val nombreUsuario: String = "",
    val email: String = ""
)

class EditProfileViewModel : ViewModel() {

    /**
     * Estado observable que contiene el perfil editable del usuario.
     */
    private val _profile = MutableStateFlow(UserProfile())
    val profile: StateFlow<UserProfile> = _profile

    /**
     * Estado observable que contiene el perfil original cargado desde la base de datos.
     */
    private val _originalProfile = MutableStateFlow<UserProfile?>(null)

    val originalProfile: StateFlow<UserProfile?> = _originalProfile


    /**
     * Carga el perfil del usuario actual desde Firestore y actualiza los estados observables.
     */
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


    /**
     * Actualiza un campo específico del perfil editable.
     *
     * @param field Nombre del campo a actualizar (por ejemplo, "nombre", "apellidoP").
     * @param value Nuevo valor para el campo.
     */
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


    /**
     * Actualiza el perfil del usuario en Firestore con los datos actuales del perfil editable.
     *
     * @param onResult Callback que indica si la actualización fue exitosa.
     */
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
