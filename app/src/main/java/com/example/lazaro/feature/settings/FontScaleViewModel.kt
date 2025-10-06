package com.example.lazaro.feature.settings

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * ViewModel encargado de gestionar la escala de fuente de la aplicación.
 *
 * Permite aumentar, disminuir o restablecer el tamaño de fuente, persistiendo la preferencia
 * del usuario mediante [FontScalePreferences]. Expone el valor actual de la escala como un [StateFlow].
 *
 * @param application Instancia de [Application] necesaria para acceder al contexto.
 */
class FontScaleViewModel(application: Application) : AndroidViewModel(application) {
    private val _fontScale = MutableStateFlow(1.0f)

    /**
     * Flujo que expone el valor actual de la escala de fuente.
     */
    val fontScale: StateFlow<Float> = _fontScale



    init {
        FontScalePreferences.getFontScale(application)
            .onEach { _fontScale.value = it }
            .launchIn(viewModelScope)
    }


    /**
     * Incrementa la escala de fuente hasta un máximo de 1.5f.
     */
    fun increaseFontScale() {
        if (_fontScale.value < 1.5f) {
            updateFontScale((_fontScale.value + 0.1f).coerceAtMost(1.5f))
        }
    }

    /**
     * Disminuye la escala de fuente hasta un mínimo de 0.8f.
     */
    fun decreaseFontScale() {
        if (_fontScale.value > 0.8f) {
            updateFontScale((_fontScale.value - 0.1f).coerceAtLeast(0.8f))
        }
    }

    /**
     * Restablece la escala de fuente al valor predeterminado (1.0f).
     */
    fun resetFontScale() {
        updateFontScale(1.0f)
    }

    private fun updateFontScale(value: Float) {
        _fontScale.value = value
        viewModelScope.launch {
            FontScalePreferences.saveFontScale(getApplication(), value)
        }
    }
}