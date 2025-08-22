package com.example.lazaro.data

import androidx.compose.runtime.mutableStateOf

data class Users(
    val id: Int,
    val nombreUsuario: String,
    val nombre: String,
    val apellidoP: String,
    val ApellidoM: String,
    val email: String,
    val password: String
)

object UsersRepository {
    private val usersList = mutableListOf<Users>()

    fun addUsers(users: Users) {
        usersList.add(users)
    }

    fun getUsers(): List<Users> {
        return usersList.toList()
    }
}

