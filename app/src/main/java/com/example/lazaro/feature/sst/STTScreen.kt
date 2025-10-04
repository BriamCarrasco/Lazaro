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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import topBarBack

@Composable
fun sttscreen(navRouter: NavController, onBack: () -> Unit) {

    val context = LocalContext.current
    val speechRecognizer = remember { SpeechRecognizer.createSpeechRecognizer(context) }
    var speechText by remember { mutableStateOf("Presione el boton para hablar ") }
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
            /*...*/
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
        }
        override fun onError(error: Int) {
            speechText = "Error al reconocer la voz, intente de nuevo."
        }
        override fun onResults(p0:  Bundle?) {
            val matches = p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            speechText = matches?.firstOrNull() ?: "No se reconoció ninguna voz."
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

            Text (text = speechText,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    permissionToRecordAudio.launch(android.Manifest.permission.RECORD_AUDIO)
                }
            ) {
                Text(text = "Hablar",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }


        }
    }
}