package com.example.co.mealapp.Api

import com.example.co.mealapp.dataModels.Categories
import com.example.co.mealapp.dataModels.MealsDetails
import com.example.co.mealapp.dataModels.MealsDetailsList
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.security.interfaces.RSAKey

interface Api {


    //Url=https://www.themealdb.com/api.php

    //getListOfCategories
    @GET("categories.php")
    suspend fun getCategories():Response<Categories>
    //search the meals form mealsName
    @GET("search.php")
    suspend fun getMealsSearchedList(@Query("s") searchedText:String ):Response<MealsDetailsList>

    //show the searched meals details
    @GET("lookup.php")
    suspend fun getMealsDetails(@Query("i") mealsID:String):Response<MealsDetailsList>
    //we use the same class from mealsDetails in the top query we don't have the ing ans mus but in this
    //we have so we use the another call in api with same class to fill it
}