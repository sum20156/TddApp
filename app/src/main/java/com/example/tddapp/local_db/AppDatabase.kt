package com.example.tddapp.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tddapp.UserBody

@Database(entities = [UserBody::class], exportSchema = true, version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao():UserDao
}