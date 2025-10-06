package com.example.lazaro.feature.settings

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

/**
 * Objeto para gestionar la persistencia de la escala de fuente en la aplicación.
 *
 * Utiliza DataStore para guardar y recuperar la preferencia de escala de fuente del usuario.
 */
val Context.fontScaleDataStore by preferencesDataStore("font_scale_prefs")
object FontScalePreferences {

    /**
     * Clave utilizada para almacenar la escala de fuente en DataStore.
     */
    val FONT_SCALE_KEY = floatPreferencesKey("font_scale")

    /**
     * Guarda el valor de la escala de fuente en DataStore.
     *
     * @param context Contexto de la aplicación.
     * @param value Valor de la escala de fuente a guardar.
     */
    suspend fun saveFontScale(context: Context, value: Float) {
        context.fontScaleDataStore.edit { prefs ->
            prefs[FONT_SCALE_KEY] = value
        }
    }

    /**
     * Obtiene el valor actual de la escala de fuente desde DataStore.
     *
     * @param context Contexto de la aplicación.
     * @return Un flujo que emite el valor de la escala de fuente, o 1.0f si no está definido.
     */
    fun getFontScale(context: Context): Flow<Float> =
        context.fontScaleDataStore.data.map { prefs ->
            prefs[FONT_SCALE_KEY] ?: 1.0f
        }
}