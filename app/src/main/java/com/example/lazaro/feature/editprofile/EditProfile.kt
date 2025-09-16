package com.example.lazaro.feature.editprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lazaro.feature.session.SessionViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import topBarBack
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

@Composable
fun EditProfile(navRouter: NavController, onBack: () -> Unit, sessionViewModel: SessionViewModel) {



    val currentUser = sessionViewModel.currentUser.value

    var nombre by remember { mutableStateOf(currentUser?.nombre ?: "") }
    var apellidoP by remember { mutableStateOf(currentUser?.apellidoP ?: "") }
    var apellidoM by remember { mutableStateOf(currentUser?.ApellidoM ?: "") }
    var username by remember { mutableStateOf(currentUser?.nombreUsuario ?: "") }
    var email by remember { mutableStateOf(currentUser?.email ?: "") }

   Scaffold(
       topBar = {
           topBarBack(
               onBackClick = onBack,
               title = ""
           )
       }
    ) { innerPadding ->
       Column(
           modifier = Modifier
               .fillMaxSize()
               .background(MaterialTheme.colorScheme.background)
               .verticalScroll(rememberScrollState())
               .imePadding()
               .padding(horizontal = 32.dp)
               .padding(innerPadding),
           verticalArrangement = Arrangement.Top,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {

           Row(){
               Text (
                   text = "Editar perfil",
                   fontSize = 28.sp,
                   fontWeight = FontWeight.ExtraBold,
                   color = MaterialTheme.colorScheme.onBackground,
                   modifier = Modifier
                       .weight(1f)
               )

               Spacer(modifier = Modifier.width(16.dp))

               Button(
                   onClick = {},
                   modifier = Modifier
                       .width(110.dp)
                       .height(48.dp),
                   colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                   shape = RoundedCornerShape(32.dp)

               ){
                   Text("Actualizar", fontSize = 16.sp, color = MaterialTheme.colorScheme.onPrimary)
               }

           }


           Spacer(modifier = Modifier.height(24.dp))

           if(currentUser != null){
               Text(
                   text = "${currentUser.nombre} ${currentUser.apellidoP}",
                   fontSize = 24.sp,
                     fontWeight = FontWeight.Normal,
                     color = MaterialTheme.colorScheme.inversePrimary,
                     modifier = Modifier
                          .align(Alignment.Start)
               )
           }

           Spacer(modifier = Modifier.height(24.dp))

           Text(
               text = "Nombre",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
               modifier = Modifier
                   .align(Alignment.Start)
           )

           Spacer(modifier = Modifier.height(4.dp))

           TextField(
               value = nombre,
               onValueChange = { nombre = it },
               modifier = Modifier.fillMaxWidth(),
               colors = TextFieldDefaults.colors(
                   focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                   unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                   focusedTextColor = MaterialTheme.colorScheme.primary,
                   unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                   focusedContainerColor = MaterialTheme.colorScheme.surface,
                   unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                   cursorColor = MaterialTheme.colorScheme.primary,
                   focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                   unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
               ),
               singleLine = true,
               placeholder = { Text("Placeholder") }
           )

           Spacer(modifier = Modifier.height(24.dp))

           Text(
               text = "Apellido Paterno",
               fontSize = 16.sp,
               fontWeight = FontWeight.SemiBold,
               color = MaterialTheme.colorScheme.onBackground,
               modifier = Modifier
                   .align(Alignment.Start)
           )
           Spacer(modifier = Modifier.height(4.dp))

           TextField(
               value = apellidoP,
               onValueChange = { apellidoP = it },
               modifier = Modifier.fillMaxWidth(),
               colors = TextFieldDefaults.colors(
                   focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                   unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                   focusedTextColor = MaterialTheme.colorScheme.primary,
                   unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                   focusedContainerColor = MaterialTheme.colorScheme.surface,
                   unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                   cursorColor = MaterialTheme.colorScheme.primary,
                   focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                   unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
               ),
               singleLine = true,
               placeholder = { Text("Placeholder") }
           )

           Spacer(modifier = Modifier.height(24.dp))

           Text(
               text = "Apellido Materno",
               fontSize = 16.sp,
               fontWeight = FontWeight.SemiBold,
               color = MaterialTheme.colorScheme.onBackground,
               modifier = Modifier
                   .align(Alignment.Start)
           )

           Spacer(modifier = Modifier.height(4.dp))

           TextField(
               value = apellidoM,
               onValueChange = { apellidoM = it },
               modifier = Modifier.fillMaxWidth(),
               colors = TextFieldDefaults.colors(
                   focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                   unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                   focusedTextColor = MaterialTheme.colorScheme.primary,
                   unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                   focusedContainerColor = MaterialTheme.colorScheme.surface,
                   unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                   cursorColor = MaterialTheme.colorScheme.primary,
                   focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                   unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
               ),
               singleLine = true,
               placeholder = { Text("Placeholder") }
           )

           Spacer(modifier = Modifier.height(24.dp))

           Text(
               text = "Nombre de usuario",
               fontSize = 16.sp,
               fontWeight = FontWeight.SemiBold,
               color = MaterialTheme.colorScheme.onBackground,
               modifier = Modifier
                   .align(Alignment.Start)
           )

           Spacer(modifier = Modifier.height(4.dp))

           TextField(
               value = username,
               onValueChange = { username = it },
               modifier = Modifier.fillMaxWidth(),
               colors = TextFieldDefaults.colors(
                   focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                   unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                   focusedTextColor = MaterialTheme.colorScheme.primary,
                   unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                   focusedContainerColor = MaterialTheme.colorScheme.surface,
                   unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                   cursorColor = MaterialTheme.colorScheme.primary,
                   focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                   unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
               ),
               singleLine = true,
               placeholder = { Text("Placeholder") }
           )

           Spacer(modifier = Modifier.height(24.dp))

           Text(
               text = "Correo electrónico",
               fontSize = 16.sp,
               fontWeight = FontWeight.SemiBold,
               color = MaterialTheme.colorScheme.onBackground,
               modifier = Modifier
                   .align(Alignment.Start)
           )

           Spacer(modifier = Modifier.height(4.dp))

           TextField(
               value = email,
               onValueChange = { email = it },
               modifier = Modifier.fillMaxWidth(),
               colors = TextFieldDefaults.colors(
                   focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                   unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                   focusedTextColor = MaterialTheme.colorScheme.primary,
                   unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                   focusedContainerColor = MaterialTheme.colorScheme.surface,
                   unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                   cursorColor = MaterialTheme.colorScheme.primary,
                   focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                   unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
               ),
               singleLine = true,
               placeholder = { Text("Placeholder") }
           )

              Spacer(modifier = Modifier.height(24.dp))

           Button(
               onClick = {},
               modifier = Modifier
               .width(250.dp)
               .height(48.dp),
               colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
               shape = RoundedCornerShape(32.dp)

           ){
               Text("Actualizar contraseña", fontSize = 16.sp, color = MaterialTheme.colorScheme.onPrimary)
           }
       }
   }
}