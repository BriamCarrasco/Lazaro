package com.example.lazaro.feature.reusable


import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.lazaro.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource


/**
 * Composable para alternar entre el tema claro y oscuro de la aplicación.
 *
 * Muestra un interruptor (Switch) que permite al usuario cambiar el tema actual.
 * El estado del tema se refleja visualmente y se notifica mediante el callback proporcionado.
 *
 * @param darkThemeEnabled Indica si el tema oscuro está activado.
 * @param onToggleTheme Acción a ejecutar cuando el usuario cambia el estado del switch.
 * @param modifier Modificador opcional para personalizar el estilo o disposición del componente.
 */
@Composable
fun ThemeSwitcherLottie(
    darkThemeEnabled: Boolean,
    onToggleTheme: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onToggleTheme() }
    ) {


        Spacer(modifier = Modifier.width(8.dp))

        Switch(
            checked = darkThemeEnabled,
            onCheckedChange = { onToggleTheme() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.inversePrimary,
                uncheckedTrackColor = MaterialTheme.colorScheme.secondary
            )
        )
    }
}