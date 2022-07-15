package com.example.tddapp.api

import com.example.tddapp.Constants
import com.example.tddapp.UserBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.USERS_COLLECTION)
    suspend fun createUser(@Body userBody: UserBody):Response<ApiResponse>
}