package com.example.co.mealapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.co.mealapp.R
import com.example.co.mealapp.Utils.*
import com.example.co.mealapp.dataModels.MealsDetails
import com.example.co.mealapp.databinding.FragmentSearchResultBinding
import com.example.co.mealapp.databinding.MainSearchFragmentBinding
import com.example.co.mealapp.ui.adaptor.SearchedMealsAdapter
import com.example.co.mealapp.ui.viewmodels.MainViewModel
import kotlin.text.StringBuilder

class SearchResultFragment : Fragment() {


    private lateinit var navController:NavController

    private val mainViewModel by activityViewModels<MainViewModel>()



    private var _binding:FragmentSearchResultBinding?=null
    private val binding get() = _binding!!
    //using nav args
    private val args:SearchResultFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            if (args.category==null){
                val action=SearchResultFragmentDirections.actionSearchResultFragmentToMainFragment()
                navController.navigate(action)
            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSearchResultBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        if(NetworkHelper.isNetworkConnected(requireContext())){
            navController=Navigation.findNavController(view)
            val mealId=args.mealId
            getMealsDetails(mealId)
        }
        else{
            requireContext().showSnack(binding.root,"Check your Internet Connection",5000)
            binding.btnCheckNet.visibility=View.VISIBLE
            binding.btnCheckNet.setOnClickListener {
                if (NetworkHelper.isNetworkConnected(requireContext())){
                    navController=Navigation.findNavController(view)
                    val mealId=args.mealId
                    getMealsDetails(mealId)                }
                else{
                    requireContext().showSnack(binding.root,"Check your Internet Connection",5000)
                }
            }
        }

    }

    private fun getMealsDetails(mealId:String){
        mainViewModel.getSearchedMealsDetails(mealId).observe(viewLifecycleOwner){
            val meals=it.data
            binding.progressBar.showIf {
                meals==null
            }
            if (it.status==Status.SUCCESS){
                val meal=meals!![0]
                initVars(meal)
                openYoutube(meal)
                openSource(meal)

            }
        }
    }


    private fun initVars(meals:MealsDetails){
        val imageUrl=meals.strMealThumb
        putMealImage(imageUrl)
        val mealName=meals.strMeal
        putMealName(mealName)
        val instruction=meals.strInstructions
        putMealInstruction(instruction)
        val category=meals.strCategory
        putMealCategory(category)
        val country=meals.strArea
        putMealCountry(country)
        addIngredientFromApiToIngredients(meals)
        addMeasureFromApiToMeasures(meals)
    }

    private fun putMealImage(imageURL:String){
        Glide.with(this).load(imageURL).into(binding.imageCategory)
    }
     private fun putMealName(mealName:String){
         binding.mealName.text=mealName
     }

    private fun putMealCategory(Category:String){
        binding.category.text=Category
    }
    private fun putMealCountry(Country:String){
        binding.country.text=Country
    }

    private fun putMealInstruction(Instruction:String){
        binding.instructions.text=Instruction
    }
    private fun putIngredient(ingredient:String){
        binding.ingredient.text=ingredient
    }
    private fun putMeasures(measure:String){
        binding.measure.text=measure
    }
    private fun getIngredient(ingredient:MutableList<String>){
        val ingredientText=StringBuilder()
        for (Ingredient:String in ingredient){
            if(Ingredient!=" "&&Ingredient.isNotEmpty()){
                ingredientText.append("\n \u2022$Ingredient")
            }
            putIngredient(ingredientText.toString())
        }
    }

    private fun addIngredientFromApiToIngredients(meal:MealsDetails){
        val ingredient= mutableListOf<String>()
        ingredient.add(meal.strIngredient1)
        ingredient.add(meal.strIngredient2)
        ingredient.add(meal.strIngredient3)
        ingredient.add(meal.strIngredient4)
        ingredient.add(meal.strIngredient5)
        ingredient.add(meal.strIngredient6)
        ingredient.add(meal.strIngredient7)
        ingredient.add(meal.strIngredient8)
        ingredient.add(meal.strIngredient9)
        ingredient.add(meal.strIngredient10)
        ingredient.add(meal.strIngredient11)
        ingredient.add(meal.strIngredient12)
        ingredient.add(meal.strIngredient13)
        ingredient.add(meal.strIngredient14)
        ingredient.add(meal.strIngredient15)
        ingredient.add(meal.strIngredient16)
        ingredient.add(meal.strIngredient17)
        ingredient.add(meal.strIngredient18)
        ingredient.add(meal.strIngredient19)
        ingredient.add(meal.strIngredient20)
        getIngredient(ingredient)
    }

    private  fun getMeasures(measures:MutableList<String>){
        val measuresText=StringBuilder()
        for(measure:String in measures){
            if (measure!=" "&& measure.isNotEmpty()){
                measuresText.append("\n \u2022$measure")
            }
            putMeasures(measuresText.toString())
        }
    }
    private fun addMeasureFromApiToMeasures(meal:MealsDetails){
        val measure= mutableListOf<String>()
        measure.add(meal.strMeasure1)
        measure.add(meal.strMeasure2)
        measure.add(meal.strMeasure3)
        measure.add(meal.strMeasure4)
        measure.add(meal.strMeasure5)
        measure.add(meal.strMeasure6)
        measure.add(meal.strMeasure7)
        measure.add(meal.strMeasure8)
        measure.add(meal.strMeasure9)
        measure.add(meal.strMeasure10)
        measure.add(meal.strMeasure11)
        measure.add(meal.strMeasure12)
        measure.add(meal.strMeasure13)
        measure.add(meal.strMeasure14)
        measure.add(meal.strMeasure15)
        measure.add(meal.strMeasure16)
        measure.add(meal.strMeasure17)
        measure.add(meal.strMeasure18)
        measure.add(meal.strMeasure19)
        measure.add(meal.strMeasure20)
            getMeasures(measure)
    }
    private fun openYoutube(meal:MealsDetails){
        binding.youtube.setOnClickListener {
            openUrlIntent(meal.strYoutube)
        }
    }

    private fun openSource(meal:MealsDetails){
        binding.links.setOnClickListener {
            openUrlIntent(meal.strSource)
        }
    }


    private fun openUrlIntent(uri: String) {
        val implicitIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        activity?.startActivity(implicitIntent)
    }



}