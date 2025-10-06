package com.example.lazaro

import com.example.lazaro.feature.login.LoginViewModel
import com.example.lazaro.feature.login.LoginState
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*


class LoginViewModelTest {


    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var viewModel: LoginViewModel
    private lateinit var mockTask: Task<AuthResult>

    @Before
    fun setUp() {
        firebaseAuth = mock(FirebaseAuth::class.java)
        mockTask = mock(Task::class.java) as Task<AuthResult>
        viewModel = LoginViewModel(firebaseAuth)
    }

    @Test
    fun `isValid retorna false si ambos campos están vacíos`() {
        viewModel.email = ""
        viewModel.password = ""
        assertFalse(viewModel.isValid())
    }

    @Test
    fun `isValid retorna false si el email está vacío`() {
        viewModel.email = ""
        viewModel.password = "1234"
        assertFalse(viewModel.isValid())
    }

    @Test
    fun `isValid retorna false si la contraseña está vacía`() {
        viewModel.email = "test@mail.com"
        viewModel.password = ""
        assertFalse(viewModel.isValid())
    }

    @Test
    fun `isValid retorna true si ambos campos están llenos`() {
        viewModel.email = "test@mail.com"
        viewModel.password = "1234"
        assertTrue(viewModel.isValid())
    }

    @Test
    fun `loginWithFirebase cambia a Success cuando el login es exitoso`() = runTest {
        `when`(firebaseAuth.signInWithEmailAndPassword(anyString(), anyString()))
            .thenReturn(mockTask)
        val captor = ArgumentCaptor.forClass(OnCompleteListener::class.java) as ArgumentCaptor<OnCompleteListener<AuthResult>>
        `when`(mockTask.addOnCompleteListener(captor.capture())).thenReturn(mockTask)
        `when`(mockTask.isSuccessful).thenReturn(true)

        viewModel.loginWithFirebase("test@mail.com", "1234")
        captor.value.onComplete(mockTask)
        assertEquals(LoginState.Success, viewModel.loginState.first())
    }

}