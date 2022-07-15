package com.example.tddapp

import android.app.Application
import com.example.tddapp.local_db.AppDatabase
import com.example.tddapp.local_db.RoomObject

class MyApp:Application() {

    companion object{
        lateinit var INSTANCE:MyApp

    }
    lateinit var roomObject: RoomObject
    override fun onCreate() {
        super.onCreate()
        INSTANCE =this
        roomObject = RoomObject(this)
    }

    fun getDB(): AppDatabase {
       return roomObject.db
    }
}