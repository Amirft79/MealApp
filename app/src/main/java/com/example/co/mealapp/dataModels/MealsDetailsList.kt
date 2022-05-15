package com.example.co.mealapp.dataModels

import com.google.gson.annotations.SerializedName

data class MealsDetailsList(
     @SerializedName("meals")
     val mealsList:MutableList<MealsDetails>
 ) {
}