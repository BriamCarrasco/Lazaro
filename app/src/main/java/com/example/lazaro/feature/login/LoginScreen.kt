package com.example.lazaro.feature.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.example.lazaro.R
import androidx.compose.foundation.layout.Row
import androidx.navigation.NavHostController
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import com.example.lazaro.feature.session.SessionViewModel
import com.example.lazaro.data.UsersRepository


@Composable
    fun loginScreen(navRouter: NavHostController, viewModel: LoginViewModel, sessionViewModel: SessionViewModel) {
        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val usuarioEnSesion = sessionViewModel.loadSession(context)
    if (usuarioEnSesion != null) {
        sessionViewModel.login(usuarioEnSesion)
        navRouter.navigate("homeScreen")
        return
    }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.pawsanimation)
            )

            val progress by animateLottieCompositionAsState(
                composition,
                iterations = LottieConstants.IterateForever,
                speed = 0.5f

            )

            LottieAnimation(
                composition = composition,
                progress = {progress},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text (
                text = "LAZARO",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text (
                text = "Iniciar sesión para continuar",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.inversePrimary
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = viewModel.userName,
                onValueChange = { viewModel.userName = it },
                placeholder = {
                    Text("Nombre de usuario",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = TextStyle(textAlign = TextAlign.Center),
                        modifier = Modifier.fillMaxWidth()

                    )
                },
                modifier = Modifier.fillMaxWidth()
                    .height(54.dp)
                    .background(Color.White, shape = RoundedCornerShape(32.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                shape = RoundedCornerShape(32.dp),
                singleLine = true,

                )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                placeholder = {
                    Text("Contraseña",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = TextStyle (textAlign = TextAlign.Center),
                        modifier = Modifier.fillMaxWidth()

                    )
                },
                modifier = Modifier.fillMaxWidth()
                    .height(54.dp)
                    .background(Color.White, shape = RoundedCornerShape(32.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                shape = RoundedCornerShape(32.dp),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) R.drawable.eye_crossedxml else R.drawable.eyexml
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.surfaceTint
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                if (viewModel.userName == "" || viewModel.password == "") {
                    Toast.makeText(navRouter.context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.login()
                    if (viewModel.loginCorrecto) {
                        val user = UsersRepository.getUsers().find {
                            it.nombreUsuario == viewModel.userName && it.password == viewModel.password
                        }
                        if (user != null) {
                            sessionViewModel.login(user)
                            sessionViewModel.saveSession(context, user)
                        }
                        navRouter.navigate("homeScreen")
                        Toast.makeText(
                            navRouter.context,
                            "Inicio de sesión exitoso",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            navRouter.context,
                            "Usuario o contraseña incorrecta",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            },
                modifier = Modifier
                    .width(250.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(32.dp)
            ) {
                Text("Iniciar sesión", fontSize = 16.sp, color = MaterialTheme.colorScheme.onPrimary)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¿Olvidaste tu contraseña?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable {
                    navRouter.navigate("recoverPassScreen")
                }
            )

            Spacer(modifier = Modifier.height(42.dp))

            Row {

                Text(
                    text = "¿No tienes una cuenta?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Registrate",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable{
                        navRouter.navigate("registerScreen")
                    }
                )
            }

        }
    }








