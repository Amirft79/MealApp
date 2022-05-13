package com.example.co.mealapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bumptech.glide.load.engine.Resource
import com.example.co.mealapp.Api.Api
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@ActivityRetainedScoped
class MainRepository  @Inject constructor(private val apiService:Api){

    fun getCategories() = liveData(Dispatchers.IO) {
        emit(com.example.co.mealapp.Utils.Resource.loading(null))
        try {
            val response=apiService.getCategories()
            if (response.isSuccessful){
                emit(com.example.co.mealapp.Utils.Resource.success(response.body()?.categories))
            }
        }catch (exception:Exception){
        }
    }


}