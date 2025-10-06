package com.example.lazaro

import com.example.lazaro.feature.register.RegisterViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class RegisterViewModelTest {

    private lateinit var mockAuth: FirebaseAuth
    private lateinit var mockDb: FirebaseFirestore
    private lateinit var mockTask: Task<AuthResult>
    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        mockAuth = mock(FirebaseAuth::class.java)
        mockDb = mock(FirebaseFirestore::class.java)
        mockTask = mock(Task::class.java) as Task<AuthResult>
        // Configura el mock para que no sea null
        `when`(mockAuth.createUserWithEmailAndPassword(anyString(), anyString()))
            .thenReturn(mockTask)
        viewModel = RegisterViewModel(mockAuth, mockDb)
    }

    @Test
    fun `valida paso 1 correctamente`() {
        viewModel.nombre = "Juan"
        viewModel.apellidoP = "Pérez"
        viewModel.ApellidoM = "López"
        assertTrue(viewModel.isStep1Valid())
    }

    @Test
    fun `valida paso 2 correctamente`() {
        viewModel.nombreUsuario = "juanito"
        viewModel.email = "juan@mail.com"
        viewModel.password = "123456"
        assertTrue(viewModel.isStep2Valid())
    }

    @Test
    fun `no permite registro con campos vacíos`() {
        viewModel.nombreUsuario = ""
        viewModel.email = ""
        viewModel.password = ""
        var callbackCalled = false
        // También puedes mockear addOnCompleteListener si lo necesitas
        `when`(mockTask.addOnCompleteListener(any())).thenReturn(mockTask)
        viewModel.registerUserFireBase { success, _ ->
            callbackCalled = true
            assert(!success)
        }
        assert(callbackCalled)
    }
}

