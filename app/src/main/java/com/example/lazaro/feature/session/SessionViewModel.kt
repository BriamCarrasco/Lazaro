package com.example.lazaro.feature.session

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lazaro.data.Users
import android.content.Context
import com.example.lazaro.data.UsersRepository
import kotlin.apply


class SessionViewModel : ViewModel(){
    var currentUser = mutableStateOf<Users?>(null)
    fun login(user: Users){
        currentUser.value = user
    }
    fun logout(){
        currentUser.value = null
    }

    fun saveSession(context: Context, user: Users?) {
        val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        prefs.edit().putString("username", user?.nombreUsuario).apply()
    }

    fun loadSession(context: Context): Users? {
        val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        val username = prefs.getString("username", null)
        return UsersRepository.getUsers().find { it.nombreUsuario == username }
    }

}