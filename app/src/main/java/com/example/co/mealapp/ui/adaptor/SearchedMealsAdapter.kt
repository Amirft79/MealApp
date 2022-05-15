package com.example.co.mealapp.ui.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.co.mealapp.dataModels.MealsDetails
import com.example.co.mealapp.dataModels.MealsDetailsList
import com.example.co.mealapp.databinding.SearchedMealsAdapterLayoutBinding
import com.example.co.mealapp.ui.MainSearchFragmentDirections

class SearchedMealsAdapter(val context: Context):
    ListAdapter<MealsDetails,SearchedMealsAdapter.SearchedMealsVH>(SearchedMealsListDiff()) {

        private var _binding:SearchedMealsAdapterLayoutBinding?=null
        private val binding
        get() = _binding!!

        private lateinit var navController:NavController
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchedMealsAdapter.SearchedMealsVH {
            val infalter=LayoutInflater.from(parent.context)
        _binding= SearchedMealsAdapterLayoutBinding.inflate(infalter,parent,false)
        return SearchedMealsVH(binding)
    }

    override fun onBindViewHolder(holder: SearchedMealsAdapter.SearchedMealsVH, position: Int) {
        val meal =getItem(position)
        val searchedMealImageUrl=meal.strMealThumb
        val searchMealName=meal.strMeal


        holder.binding.SearchedMealsText.text=searchMealName
        Glide.with(context).load(searchedMealImageUrl).into(holder.binding.ivSearchedMeals)
        holder.itemView.setOnClickListener {
            navController=Navigation.findNavController(it)
            val action=MainSearchFragmentDirections.actionMainFragmentToSearchResultFragment()
            navController.navigate(action)
        }
    }


    class SearchedMealsVH(val binding:SearchedMealsAdapterLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}


    class SearchedMealsListDiff : DiffUtil.ItemCallback<MealsDetails>() {
        override fun areItemsTheSame(oldItem: MealsDetails, newItem: MealsDetails): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem:MealsDetails, newItem:MealsDetails): Boolean {
            return oldItem == newItem
        }
    }


}
