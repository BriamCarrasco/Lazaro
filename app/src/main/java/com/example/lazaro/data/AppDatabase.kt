package com.example.lazaro.data

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room

@Database(entities = [Users::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "lazaro-db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}