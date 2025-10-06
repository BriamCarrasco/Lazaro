package com.example.lazaro.feature.sst


import ads_mobile_sdk.p0
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import topBarBack


/**
 * Pantalla de reconocimiento de voz a texto (STT) de la aplicación.
 *
 * Permite al usuario dictar texto mediante la voz, utilizando el motor de reconocimiento de voz del dispositivo.
 * Solicita el permiso de grabación de audio, inicia la escucha y muestra el texto reconocido en pantalla.
 *
 * @param navRouter Controlador de navegación para gestionar el flujo entre pantallas.
 * @param onBack Acción a ejecutar cuando se presiona el botón de retroceso.
 */
@Composable
fun sttscreen(navRouter: NavController, onBack: () -> Unit) {

    val context = LocalContext.current
    val speechRecognizer = remember { SpeechRecognizer.createSpeechRecognizer(context) }
    var speechText by remember { mutableStateOf("Presione el boton para hablar ") }
    var isListening by remember { mutableStateOf(false) }
    val recognizerIntent = remember {
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply{
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES")
        }
    }

    val permissionToRecordAudio = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ){
        isGranted ->
        if(isGranted){
            speechRecognizer.startListening(recognizerIntent)
        } else {
            Toast.makeText(context, "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }

    val recognizeListener = object : android.speech.RecognitionListener {
        override fun onReadyForSpeech(p0: Bundle?) {
            isListening = true
        }
        override fun onBeginningOfSpeech() {
            speechText = "Escuchando..."
        }
        override fun onRmsChanged(p0: Float) {
            /*...*/
        }
        override fun onBufferReceived(p0: ByteArray?) {
            /*...*/
        }
        override fun onEndOfSpeech() {
            speechText = "Procesando..."
            isListening = false
        }
        override fun onError(error: Int) {
            speechText = "Error al reconocer la voz, intente de nuevo."
            isListening = false
        }
        override fun onResults(p0:  Bundle?) {
            val matches = p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            speechText = matches?.firstOrNull() ?: "No se reconoció ninguna voz."
            isListening = false
        }
        override fun onPartialResults(partialResults: Bundle?) {
            /*...*/
        }
        override fun onEvent(eventType: Int, params: Bundle?) {
            /*...*/
        }
    }

    speechRecognizer.setRecognitionListener(recognizeListener)


    Scaffold(
        topBar = {
            topBarBack(
                onBackClick = onBack,
                title = ""
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(horizontal = 32.dp)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Text (
                text = "Voz a texto",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = speechText,
                onValueChange = {},
                readOnly = true,
                singleLine = false,
                placeholder = {
                    Text("Escribe el texto aquí",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center

                    )
                },
                modifier = Modifier.fillMaxWidth()
                    .height(250.dp)
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
            )


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    permissionToRecordAudio.launch(android.Manifest.permission.RECORD_AUDIO)
                },
                enabled = !isListening
            ) {
                Text(text = "Hablar",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }


        }
    }
}