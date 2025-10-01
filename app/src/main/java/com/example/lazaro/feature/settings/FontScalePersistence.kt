package com.example.lazaro.feature.settings

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore


val Context.fontScaleDataStore by preferencesDataStore("font_scale_prefs")
object FontScalePreferences {
    val FONT_SCALE_KEY = floatPreferencesKey("font_scale")
    suspend fun saveFontScale(context: Context, value: Float) {
        context.fontScaleDataStore.edit { prefs ->
            prefs[FONT_SCALE_KEY] = value
        }
    }
    fun getFontScale(context: Context): Flow<Float> =
        context.fontScaleDataStore.data.map { prefs ->
            prefs[FONT_SCALE_KEY] ?: 1.0f
        }
}