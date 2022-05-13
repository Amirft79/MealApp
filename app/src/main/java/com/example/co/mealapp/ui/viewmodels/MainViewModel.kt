package com.example.co.mealapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.co.mealapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository:MainRepository):ViewModel() {
    fun getCategories()=repository.getCategories()
}