package com.example.lazaro.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
fun getScaledTypography(fontScale: Float): Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = (16 * fontScale).sp,
        lineHeight = (24 * fontScale).sp,
        letterSpacing = (0.5 * fontScale).sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = (32 * fontScale).sp,
        lineHeight = (28 * fontScale).sp,
        letterSpacing = (0 * fontScale).sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = (14 * fontScale).sp,
        lineHeight = (16 * fontScale).sp,
        letterSpacing = (0.5 * fontScale).sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = (24 * fontScale).sp,
        lineHeight = (16 * fontScale).sp,
        letterSpacing = (0.5 * fontScale).sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = (20 * fontScale).sp,
        lineHeight = (16 * fontScale).sp,
        letterSpacing = (0.5 * fontScale).sp
    ),




)


