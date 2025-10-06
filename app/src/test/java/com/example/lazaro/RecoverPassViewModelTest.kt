package com.example.lazaro

import com.example.lazaro.feature.recoverypass.RecoverPassState
import com.example.lazaro.feature.recoverypass.RecoverViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class RecoverPassViewModelTest {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var viewModel: RecoverViewModel
    private lateinit var mockTask: Task<Void>

    @Before
    fun setUp() {
        firebaseAuth = mock(FirebaseAuth::class.java)
        mockTask = mock(Task::class.java) as Task<Void>
        viewModel = RecoverViewModel(firebaseAuth)
    }

    @Test
    fun `sendPasswordReset emite Success cuando es exitoso`() = runTest {
        `when`(firebaseAuth.sendPasswordResetEmail(anyString())).thenReturn(mockTask)

        val captor = ArgumentCaptor.forClass(com.google.android.gms.tasks.OnCompleteListener::class.java) as ArgumentCaptor<com.google.android.gms.tasks.OnCompleteListener<Void>>
        `when`(mockTask.addOnCompleteListener(captor.capture())).thenReturn(mockTask)
        `when`(mockTask.isSuccessful).thenReturn(true)

        viewModel.sendPasswordReset("test@mail.com")
        captor.value.onComplete(mockTask)

        assertTrue(viewModel.state.value is RecoverPassState.Success)
    }

    @Test
    fun `sendPasswordReset emite Error cuando falla`() = runTest {
        `when`(firebaseAuth.sendPasswordResetEmail(anyString())).thenReturn(mockTask)

        val captor = ArgumentCaptor.forClass(com.google.android.gms.tasks.OnCompleteListener::class.java) as ArgumentCaptor<com.google.android.gms.tasks.OnCompleteListener<Void>>
        `when`(mockTask.addOnCompleteListener(captor.capture())).thenReturn(mockTask)
        `when`(mockTask.isSuccessful).thenReturn(false)
        `when`(mockTask.exception).thenReturn(Exception("Correo no encontrado"))


        viewModel.sendPasswordReset("test@mail.com")
        captor.value.onComplete(mockTask)

        assertTrue(viewModel.state.value is RecoverPassState.Error)
    }
}