package com.example.co.mealapp.Api

import com.example.co.mealapp.dataModels.Categories
import com.example.co.mealapp.dataModels.MealsDetailsList
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {


    //Url=https://www.themealdb.com/api.php

    //getListOfCategories
    @GET("categories.php")
    suspend fun getCategories():Response<Categories>

    @GET("search.php")
    suspend fun getMealsSearchedList(@Query("s") searchedText:String ):Response<MealsDetailsList>
}