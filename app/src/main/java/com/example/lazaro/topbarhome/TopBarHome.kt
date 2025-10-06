package com.example.lazaro.topbarhome

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme


/**
 * Barra superior personalizada para la pantalla principal de la aplicación.
 *
 * Muestra un título y un botón de menú en la parte izquierda.
 *
 * @param title Título que se muestra en la barra superior.
 * @param onMenuClick Acción a ejecutar cuando se presiona el botón de menú.
 * @param modifier Modificador opcional para personalizar el diseño.
 * @param containerColor Color de fondo de la barra superior.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(
    title: String,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.background
){
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Default.Menu, contentDescription = "Abrir panel")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        modifier = modifier
    )
}