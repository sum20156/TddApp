package com.example.tddapp.local_db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tddapp.Constants
import com.example.tddapp.MyApp
import com.example.tddapp.api.RetrofitObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RoomObject(context: Context) {

    val db =Room.databaseBuilder(
        context,
        AppDatabase::class.java,"app_db"
    ).build()
}