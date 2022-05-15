package com.example.co.mealapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.co.mealapp.R
import com.example.co.mealapp.Utils.*
import com.example.co.mealapp.dataModels.Category
import com.example.co.mealapp.databinding.MainSearchFragmentBinding
import com.example.co.mealapp.ui.adaptor.CategoriesAdapter
import com.example.co.mealapp.ui.adaptor.SearchedMealsAdapter
import com.example.co.mealapp.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainSearchFragment : Fragment() {

    private lateinit var navController:NavController

    val mainViewModel by activityViewModels<MainViewModel>()

    private lateinit var searchedMealAdapter: SearchedMealsAdapter



    private var _binding:MainSearchFragmentBinding?=null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= MainSearchFragmentBinding.inflate(inflater,container,false)
        return binding.root





    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVars()
        searchAction()
        initSearchedMealsAdapter()
    }

    private fun initVars(){
        showKeyboard(requireContext(),binding.searchedEditText)
        searchedMealAdapter= SearchedMealsAdapter(requireContext())

    }


    private fun searchAction(){
        binding.searchedEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mainViewModel.getSearchedMeals(p0.toString()).observe(viewLifecycleOwner){
                    when(it.status){
                        Status.LOADING->{
                            binding.searchProgress.show()
                            binding.view.show()
                        }
                        Status.SUCCESS->{
                            val mealsList=it.data
                            binding.noMealsText.showIf {
                                mealsList==null
                            }
                            searchedMealAdapter.submitList(mealsList)
                            binding.searchProgress.hide()
                            binding.view.hide()
                        }
                        Status.FAIL -> {
                            Toast.makeText(requireContext(),"failed get data ", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun initSearchedMealsAdapter(){
        val snapHelper= LinearSnapHelper()
        binding.mealSearchResultAdapter.apply {
            visibility=View.VISIBLE
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
            snapHelper.attachToRecyclerView(this)
            adapter=searchedMealAdapter
        }
    }





    companion object {
        private const val TAG = "SearchFragment"
    }


}