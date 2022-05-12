package com.example.co.mealapp.Api

import com.example.co.mealapp.dataModels.Categories
import retrofit2.Response
import retrofit2.http.GET

interface Api {


    //Url=https://www.themealdb.com/api.php

    //getListOfCategories
    @GET("categories.php")
    suspend fun getCategories():Response<Categories>
}