package com.example.tddapp.ui.main_act

import com.example.tddapp.UserBody
import com.example.tddapp.api.ApiResponse
import com.example.tddapp.api.ApiService
import com.example.tddapp.local_db.AppDatabase
import okhttp3.ResponseBody
import retrofit2.Response

class MainActRepo(val remoteDB:ApiService,val localDb:AppDatabase) {


    suspend fun createUser(userBody: UserBody): Response<ApiResponse> {
      return  remoteDB.createUser(userBody)
    }

    suspend fun insertUserLocal(userBody: UserBody){
        localDb.userDao().insert(userBody)
    }

    suspend fun updateUserLocal(userBody: UserBody){
        localDb.userDao().update(userBody)
    }

    suspend fun deleteUserLocal(userBody: UserBody){
        localDb.userDao().delete(userBody)
    }

    suspend fun getAllUserLocal(): List<UserBody> {
       return localDb.userDao().getAll()
    }
}