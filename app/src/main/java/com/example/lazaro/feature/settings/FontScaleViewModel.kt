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


class FontScaleViewModel(application: Application) : AndroidViewModel(application) {
    private val _fontScale = MutableStateFlow(1.0f)
    val fontScale: StateFlow<Float> = _fontScale



    init {
        FontScalePreferences.getFontScale(application)
            .onEach { _fontScale.value = it }
            .launchIn(viewModelScope)
    }

    fun increaseFontScale() {
        if (_fontScale.value < 1.5f) {
            updateFontScale((_fontScale.value + 0.1f).coerceAtMost(1.5f))
        }
    }

    fun decreaseFontScale() {
        if (_fontScale.value > 0.8f) {
            updateFontScale((_fontScale.value - 0.1f).coerceAtLeast(0.8f))
        }
    }

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