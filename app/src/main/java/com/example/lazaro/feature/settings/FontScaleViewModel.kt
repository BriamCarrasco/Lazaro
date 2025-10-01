package com.example.lazaro.feature.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FontScaleViewModel : ViewModel() {
    private val _fontScale = MutableStateFlow(1.0f)
    val fontScale: StateFlow<Float> = _fontScale

    fun increaseFontScale() {
        if (_fontScale.value < 1.5f) _fontScale.value += 0.1f
    }

    fun decreaseFontScale() {
        if (_fontScale.value > 0.8f) _fontScale.value -= 0.1f
    }

    fun resetFontScale() {
        _fontScale.value = 1.0f
    }
}