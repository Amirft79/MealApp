package com.example.co.mealapp.Utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun Context.showToast(massage:String, duration:Int= Toast.LENGTH_SHORT){
    Toast.makeText(this,massage,duration).show()
}
fun Context.showSnack(view: View,massage:String,duration:Int=Snackbar.LENGTH_LONG){
    Snackbar.make(view,massage,duration).show()

}