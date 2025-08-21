package com.example.lazaro.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.example.lazaro.R
import androidx.compose.foundation.layout.Row


class LoginScreen {
    @Preview
    @Composable
    fun loginScreen() {
        var userName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
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
                iterations = LottieConstants.IterateForever

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
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text (
                text = "Iniciar sesión para continuar",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                placeholder = {
                    Text("Nombre de usuario",
                        color = Color.Gray,
                        style = TextStyle(textAlign = TextAlign.Center),
                        modifier = Modifier.fillMaxWidth()

                    )
                },
                modifier = Modifier.fillMaxWidth()
                    .height(54.dp)
                    .background(Color.White, shape = RoundedCornerShape(32.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFD9C00),
                    unfocusedTextColor = Color(0xFFFD9C00),
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(32.dp),
                singleLine = true,

                )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text("Contraseña",
                        color = Color.Gray,
                        style = TextStyle (textAlign = TextAlign.Center),
                        modifier = Modifier.fillMaxWidth()

                    )
                },
                modifier = Modifier.fillMaxWidth()
                    .height(54.dp)
                    .background(Color.White, shape = RoundedCornerShape(32.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFD9C00),
                    unfocusedTextColor = Color(0xFFFD9C00),
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(32.dp),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) R.drawable.visible else R.drawable.ojo
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                            modifier = Modifier.size(16.dp),
                        )
                    }}
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {

            },
                modifier = Modifier
                    .width(250.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7700)),
                shape = RoundedCornerShape(32.dp)) {

                Text("Iniciar sesión", fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¿Olvidaste tu contraseña?",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1E88E5),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(42.dp))

            Row {

                Text(
                    text = "¿No tienes una cuenta?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Registrate",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF1E88E5),
                    textAlign = TextAlign.Center,
                )
            }

        }
    }
}





