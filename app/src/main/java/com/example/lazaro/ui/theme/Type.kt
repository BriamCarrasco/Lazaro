package com.example.lazaro.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Genera un conjunto de estilos tipográficos escalables para la aplicación,
 * adaptando el tamaño de fuente, interlineado y espaciado según el factor de escala proporcionado.
 *
 * @param fontScale Factor de escala para ajustar el tamaño de las fuentes.
 * @return Un objeto [Typography] con los estilos personalizados.
 */

fun getScaledTypography(fontScale: Float): Typography = Typography(

    /**
     * Estilos personalizados para aplicación.
     */


    /**
     * Estilo para textos principales del cuerpo.
     */
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = (16 * fontScale).sp,
        lineHeight = (24 * fontScale).sp,
        letterSpacing = (0.5 * fontScale).sp
    ),

    /**
     * Estilo para títulos grandes.
     */
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = (32 * fontScale).sp,
        lineHeight = (28 * fontScale).sp,
        letterSpacing = (0 * fontScale).sp
    ),

    /**
     * Estilo para etiquetas pequeñas.
     */
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = (14 * fontScale).sp,
        lineHeight = (16 * fontScale).sp,
        letterSpacing = (0.5 * fontScale).sp
    ),

    /**
     * Estilo para títulos medianos.
     */
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = (24 * fontScale).sp,
        lineHeight = (16 * fontScale).sp,
        letterSpacing = (0.5 * fontScale).sp
    ),

    /**
     * Estilo para títulos pequeños.
     */
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = (20 * fontScale).sp,
        lineHeight = (16 * fontScale).sp,
        letterSpacing = (0.5 * fontScale).sp
    ),

)


