import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


/**
 * Barra superior reutilizable con botón de retroceso.
 *
 * Muestra un título centrado y un ícono de flecha para volver atrás.
 * Permite personalizar el color de fondo y el estilo mediante un [Modifier].
 *
 * @param onBackClick Acción a ejecutar cuando se presiona el botón de retroceso.
 * @param title Título a mostrar en la barra superior.
 * @param modifier Modificador opcional para personalizar el estilo o disposición del componente.
 * @param containerColor Color de fondo de la barra superior.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBarBack(
    onBackClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.background
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        modifier = modifier
    )
}