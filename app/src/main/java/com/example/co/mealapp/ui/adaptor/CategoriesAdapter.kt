package com.example.co.mealapp.ui.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.co.mealapp.R
import com.example.co.mealapp.dataModels.Category

class CategoriesAdapter(val context:Context):ListAdapter<Category,CategoriesAdapter.CategoriesVH>(MealsListDiffUtil()) {
    private lateinit var navController: NavController

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesAdapter.CategoriesVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_layout, parent, false)
        return CategoriesVH(view)
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.CategoriesVH, position: Int) {
        val category = getItem(position)
        val categoryImageUrl = category.strCategoryThumb
        val categoryName = category.strCategory

        Glide.with(context).load(categoryImageUrl).into(holder.categoryImage)
        holder.categoryName.text = categoryName

    }



    class CategoriesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImage: ImageView = itemView.findViewById(R.id.category_image)
        val categoryName: TextView = itemView.findViewById(R.id.category_name)
    }






    //for do not show same item
    class MealsListDiffUtil : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }
        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}



