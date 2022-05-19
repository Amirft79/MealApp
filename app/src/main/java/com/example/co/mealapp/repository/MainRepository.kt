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
    //show the meals  by list
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

    //searched meals by name
    fun getSearchedMeals(searchedText:String)= liveData(Dispatchers.IO){
             emit(com.example.co.mealapp.Utils.Resource.loading(null))
        try {
            val data =apiService.getMealsSearchedList(searchedText).body()?.mealsList
            emit(com.example.co.mealapp.Utils.Resource.success(data))
        }catch (exception:Exception){
            com.example.co.mealapp.Utils.Resource.failed(null,exception.message.toString())
        }
    }
    //searched the details of the meals in list
    fun getSearchedMealsDetailsList(mealId:String)= liveData (Dispatchers.IO){

        emit(com.example.co.mealapp.Utils.Resource.loading(null))
        try {
            val data=apiService.getMealsDetails(mealId).body()
            emit(com.example.co.mealapp.Utils.Resource.success(data!!.mealsList))
        }catch (exception:Exception){
            emit(com.example.co.mealapp.Utils.Resource.failed(null,exception.message.toString()))
        }
    }

}