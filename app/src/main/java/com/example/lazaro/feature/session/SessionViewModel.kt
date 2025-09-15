package com.example.lazaro.feature.session

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lazaro.data.Users
import android.content.Context


class SessionViewModel : ViewModel() {
    var currentUser = mutableStateOf<Users?>(null)

    fun login(user: Users) {
        currentUser.value = user
    }
    fun logout() {
        currentUser.value = null
    }

    fun saveSession(context: Context, user: Users?) {
        val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        prefs.edit().putString("username", user?.nombreUsuario).apply()
    }

    fun loadSession(context: Context): String? {
        val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        return prefs.getString("username", null)
    }
}