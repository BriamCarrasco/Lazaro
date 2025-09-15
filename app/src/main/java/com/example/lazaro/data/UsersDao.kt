package com.example.lazaro.data

import androidx.room.*


@Dao
interface UsersDao {
    @Insert
    suspend fun insert(users: Users)

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<Users>

    @Query("SELECT * FROM users WHERE nombreUsuario = :nombreUsuario AND password = :password")
    suspend fun login(nombreUsuario: String, password: String): Users?

    @Query("SELECT * FROM users WHERE nombreUsuario = :username")
    suspend fun getUserByUsername(username: String): Users?
}

