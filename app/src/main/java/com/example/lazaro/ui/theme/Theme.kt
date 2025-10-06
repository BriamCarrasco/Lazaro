package com.example.lazaro.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color



/**
 * Paleta de colores personalizada para el modo oscuro de la aplicación.
 * Define los colores principales, secundarios, de fondo, superficie y de error.
 */
object DarkAppColors {

    //Definición de colores para el tema oscuro

    val primary = Color(0xFFFF7700)
    val onPrimary = Color(0xFF452B00)
    val primaryContainer = Color(0xFF624000)
    val onPrimaryContainer = Color(0xFFFFDBC7)
    val secondary = Color(0xFFE4C0AA)
    val onSecondary = Color(0xFF422C1A)
    val secondaryContainer = Color(0xFF5B422F)
    val onSecondaryContainer = Color(0xFFFFDBC7)
    val tertiary = Color(0xFFCBC8B3)
    val onTertiary = Color(0xFF333222)
    val tertiaryContainer = Color(0xFF4A4938)
    val onTertiaryContainer = Color(0xFFE8E4CE)
    val error = Color(0xFFFFB4AB)
    val errorContainer = Color(0xFF93000A)
    val onError = Color(0xFF690005)
    val onErrorContainer = Color(0xFFFFDAD6)
    val background = Color(0xFF1F1B16)
    val onBackground = Color(0xFFEAE1D9)
    val surface = Color(0xFF1F1B16)
    val onSurface = Color(0xFFEAE1D9)
    val surfaceVariant = Color(0xFF514539)
    val onSurfaceVariant = Color(0xFFD6C4B4)
    val outline = Color(0xFF9E8E81)
    val inverseOnSurface = Color(0xFF1F1B16)
    val inverseSurface = Color(0xFFEAE1D9)
    val inversePrimary = Color(0xFF80562D)
    val surfaceTint = Color(0xFFFFB786)
    val surfaceContainer = Color(0xFF26201B)
    val surfaceContainerHigh = Color(0xFF312B26)
    val surfaceContainerHighest = Color(0xFF3C3630)
    val surfaceContainerLow = Color(0xFF211C18)
    val surfaceContainerLowest = Color(0xFF16110D)
    val surfaceDim = Color(0xFF1F1B16)
    val surfaceBright = Color(0xFF3C3630)
}


/**
 * Paleta de colores personalizada para el modo claro de la aplicación.
 * Define los colores principales, secundarios, de fondo, superficie y de error.
 */
object LightAppColors {

    // Definición de colores para el tema claro

    val primary = Color(0xFFFF7700)
    val onPrimary = Color(0xFFFFFFFF)
    val primaryContainer = Color(0xFFFFDBC7)
    val onPrimaryContainer = Color(0xFF2A1600)
    val secondary = Color(0xFF755844)
    val onSecondary = Color(0xFFFFFFFF)
    val secondaryContainer = Color(0xFFFFDBC7)
    val onSecondaryContainer = Color(0xFF2B1707)
    val tertiary = Color(0xFF62604E)
    val onTertiary = Color(0xFFFFFFFF)
    val tertiaryContainer = Color(0xFFE8E4CE)
    val onTertiaryContainer = Color(0xFF1E1D0F)
    val error = Color(0xFFBA1A1A)
    val errorContainer = Color(0xFFFFDAD6)
    val onError = Color(0xFFFFFFFF)
    val onErrorContainer = Color(0xFF410002)
    val background = Color(0xFFFFFBFF)
    val onBackground = Color(0xFF1F1B16)
    val surface = Color(0xFFFFFBFF)
    val onSurface = Color(0xFF1F1B16)
    val surfaceVariant = Color(0xFFF3DDBB)
    val onSurfaceVariant = Color(0xFF514539)
    val outline = Color(0xFF84756A)
    val inverseOnSurface = Color(0xFF35302A)
    val inverseSurface = Color(0xFF35302A)
    val inversePrimary = Color(0xFFFFB786)
    val surfaceTint = Color(0xFFFF7700)
    val surfaceContainer = Color(0xFFF3E7D8)
    val surfaceContainerHigh = Color(0xFFEFE2CF)
    val surfaceContainerHighest = Color(0xFFEBE0C9)
    val surfaceContainerLow = Color(0xFFF8F0E2)
    val surfaceContainerLowest = Color(0xFFFFFFFF)
    val surfaceDim = Color(0xFFE2D6C6)
    val surfaceBright = Color(0xFFFFF9F3)
}


/**
 * Esquema de colores para el tema claro, basado en [LightAppColors].
 */
private val LightColorScheme = lightColorScheme(

    // Asignación de colores del tema claro

    primary = LightAppColors.primary,
    onPrimary = LightAppColors.onPrimary,
    primaryContainer = LightAppColors.primaryContainer,
    onPrimaryContainer = LightAppColors.onPrimaryContainer,
    secondary = LightAppColors.secondary,
    onSecondary = LightAppColors.onSecondary,
    secondaryContainer = LightAppColors.secondaryContainer,
    onSecondaryContainer = LightAppColors.onSecondaryContainer,
    tertiary = LightAppColors.tertiary,
    onTertiary = LightAppColors.onTertiary,
    tertiaryContainer = LightAppColors.tertiaryContainer,
    onTertiaryContainer = LightAppColors.onTertiaryContainer,
    error = LightAppColors.error,
    errorContainer = LightAppColors.errorContainer,
    onError = LightAppColors.onError,
    onErrorContainer = LightAppColors.onErrorContainer,
    background = LightAppColors.background,
    onBackground = LightAppColors.onBackground,
    surface = LightAppColors.surface,
    onSurface = LightAppColors.onSurface,
    surfaceVariant = LightAppColors.surfaceVariant,
    onSurfaceVariant = LightAppColors.onSurfaceVariant,
    outline = LightAppColors.outline,
    inverseOnSurface = LightAppColors.inverseOnSurface,
    inverseSurface = LightAppColors.inverseSurface,
    inversePrimary = LightAppColors.inversePrimary,
    surfaceTint = LightAppColors.surfaceTint,
    surfaceContainer = LightAppColors.surfaceContainer,
    surfaceContainerHigh = LightAppColors.surfaceContainerHigh,
    surfaceContainerHighest = LightAppColors.surfaceContainerHighest,
    surfaceContainerLow = LightAppColors.surfaceContainerLow,
    surfaceContainerLowest = LightAppColors.surfaceContainerLowest,
    surfaceDim = LightAppColors.surfaceDim,
    surfaceBright = LightAppColors.surfaceBright
)

/**
 * Esquema de colores para el tema oscuro, basado en [DarkAppColors].
 */
private val DarkColorScheme = darkColorScheme(

    // Asignación de colores del tema oscur

    primary = DarkAppColors.primary,
    onPrimary = DarkAppColors.onPrimary,
    primaryContainer = DarkAppColors.primaryContainer,
    onPrimaryContainer = DarkAppColors.onPrimaryContainer,
    secondary = DarkAppColors.secondary,
    onSecondary = DarkAppColors.onSecondary,
    secondaryContainer = DarkAppColors.secondaryContainer,
    onSecondaryContainer = DarkAppColors.onSecondaryContainer,
    tertiary = DarkAppColors.tertiary,
    onTertiary = DarkAppColors.onTertiary,
    tertiaryContainer = DarkAppColors.tertiaryContainer,
    onTertiaryContainer = DarkAppColors.onTertiaryContainer,
    error = DarkAppColors.error,
    errorContainer = DarkAppColors.errorContainer,
    onError = DarkAppColors.onError,
    onErrorContainer = DarkAppColors.onErrorContainer,
    background = DarkAppColors.background,
    onBackground = DarkAppColors.onBackground,
    surface = DarkAppColors.surface,
    onSurface = DarkAppColors.onSurface,
    surfaceVariant = DarkAppColors.surfaceVariant,
    onSurfaceVariant = DarkAppColors.onSurfaceVariant,
    outline = DarkAppColors.outline,
    inverseOnSurface = DarkAppColors.inverseOnSurface,
    inverseSurface = DarkAppColors.inverseSurface,
    inversePrimary = DarkAppColors.inversePrimary,
    surfaceTint = DarkAppColors.surfaceTint,
    surfaceContainer = DarkAppColors.surfaceContainer,
    surfaceContainerHigh = DarkAppColors.surfaceContainerHigh,
    surfaceContainerHighest = DarkAppColors.surfaceContainerHighest,
    surfaceContainerLow = DarkAppColors.surfaceContainerLow,
    surfaceContainerLowest = DarkAppColors.surfaceContainerLowest,
    surfaceDim = DarkAppColors.surfaceDim,
    surfaceBright = DarkAppColors.surfaceBright
)




/**
 * Composable que aplica el tema visual de la aplicación Lazaro.
 *
 * Selecciona el esquema de colores (claro, oscuro o dinámico) y la tipografía escalable.
 *
 * @param darkTheme Indica si se debe usar el tema oscuro.
 * @param dynamicColor Si es `true`, usa colores dinámicos en Android 12+.
 * @param fontScale Factor de escala para el tamaño de fuente.
 * @param content Contenido composable al que se aplica el tema.
 */
@Composable
fun LazaroTheme(
    darkTheme: Boolean,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    fontScale: Float = 1.0f,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = getScaledTypography(fontScale),
        content = content
    )
}