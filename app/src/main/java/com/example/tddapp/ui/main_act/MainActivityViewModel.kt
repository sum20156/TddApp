package com.example.tddapp.ui.main_act

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tddapp.MyApp
import com.example.tddapp.UserBody
import com.example.tddapp.api.RetrofitObject
import kotlinx.coroutines.launch

class MainActivityViewModel:ViewModel() {

    val db = MyApp.INSTANCE.getDB()
    val mainActRepo = MainActRepo(RetrofitObject.apiService,db)


    fun createUser(userBody: UserBody){
        viewModelScope.launch {
            val response = mainActRepo.createUser(userBody)
            if (response.isSuccessful) mainActRepo.insertUserLocal(userBody)
        }

    }



}