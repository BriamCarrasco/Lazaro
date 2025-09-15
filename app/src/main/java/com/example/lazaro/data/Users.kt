package com.example.lazaro.data

import androidx.compose.runtime.mutableStateOf
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombreUsuario: String,
    val nombre: String,
    val apellidoP: String,
    val ApellidoM: String,
    val email: String,
    val password: String
)



/*
object UsersRepository {
    private val usersList = mutableListOf<Users>()

    fun addUsers(users: Users) {
        usersList.add(users)
    }

    fun getUsers(): List<Users> {
        return usersList.toList()
    }

    fun usersPrecargados(){
        usersList.addAll(
            listOf(
                Users(1, "juanp", "Juan", "Perez", "Lopez", "jperezl@email.com", "qwerty123"),
                Users(2, "rominac", "Romina", "Carrasco", "Gonzalez", "rcarrascog@email.com", "qwerty123"),
                Users(3, "ivang", "Ivan", "Godoy", "Valedebenito", "Igodoyv@email.com", "qwerty123"),
                Users(2, "briamc", "Briam", "Carrasco", "Fuentes", "bcarrascof@email.com", "qwerty123"),
                Users(3, "lazaror", "Lazaro", "Ramirez", "Benavente", "lramirezb@email.com", "qwerty123"),

                )
        )
    }

}
*/


