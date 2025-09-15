package com.example.lazaro.data

import com.example.lazaro.data.Users
import com.example.lazaro.data.UsersDao

class UsersRoomRepository(private val usersDao: UsersDao) {
    suspend fun insertUser(user: Users) = usersDao.insert(user)
    suspend fun getAllUsers(): List<Users> = usersDao.getAll()
    suspend fun login(nombreUsuario: String, password: String): Users? =
        usersDao.login(nombreUsuario, password)

    suspend fun getUserByUsername(username: String): Users? {
        return usersDao.getUserByUsername(username)
    }
}

