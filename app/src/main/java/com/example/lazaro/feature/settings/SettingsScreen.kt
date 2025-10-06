package com.example.lazaro.feature.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import topBarBack
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.lazaro.feature.reusable.ThemeSwitcherLottie
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.ui.res.painterResource
import com.example.lazaro.R
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Pantalla de configuración de la aplicación.
 *
 * Permite al usuario cambiar el tema (claro/oscuro) y ajustar el tamaño de fuente para mejorar la accesibilidad.
 * Incluye una barra superior con botón de retroceso y utiliza un ViewModel para gestionar la escala de fuente.
 *
 * @param darkThemeEnabled Indica si el tema oscuro está activado.
 * @param onToggleTheme Acción a ejecutar al alternar el tema.
 * @param navRouter Controlador de navegación para gestionar el flujo entre pantallas.
 * @param onBack Acción a ejecutar al presionar el botón de retroceso.
 * @param fontScaleViewModel ViewModel encargado de gestionar el tamaño de fuente.
 */
@Composable
fun SettingsScreen(
    darkThemeEnabled: Boolean,
    onToggleTheme: () -> Unit,
    navRouter: NavController,
    onBack: () -> Unit,
    fontScaleViewModel: FontScaleViewModel = viewModel()
    ) {
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
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text (
                text = "Configuraciones",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.Start)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Accesibilidad",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier.align(Alignment.Start)
                    .padding(16.dp)

            )

            HorizontalDivider()

            ListItem(
                headlineContent = {
                    Text("Activar tema oscuro",
                    style = MaterialTheme.typography.bodyLarge) },
                trailingContent = {
                    ThemeSwitcherLottie(
                        darkThemeEnabled = darkThemeEnabled,
                        onToggleTheme = onToggleTheme
                    )
                }
            )

            HorizontalDivider()

            ListItem(
                headlineContent = {
                    Text("Tamaño de fuente",
                    style = MaterialTheme.typography.bodyLarge)},
                trailingContent = {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.menos),
                            contentDescription = "disminuir",
                            modifier = Modifier.size(32.dp)
                                .clip(CircleShape)
                                .clickable {fontScaleViewModel.decreaseFontScale()},
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),

                        )

                        Spacer(modifier = Modifier.width(24.dp))

                        Image(
                            painter = painterResource(id = R.drawable.mas),
                            contentDescription = "aumentar",
                            modifier = Modifier.size(32.dp)
                                .clip(CircleShape)
                                .clickable {fontScaleViewModel.increaseFontScale()},
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                        )

                        Spacer(modifier = Modifier.width(24.dp))

                        Image(
                            painter = painterResource(id = R.drawable.reset),
                            contentDescription = "restablecer",
                            modifier = Modifier.size(32.dp)
                                .clip(CircleShape)
                                .clickable {fontScaleViewModel.resetFontScale()},
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                        )
                    }
                }
            )

            HorizontalDivider()




        }
    }
}