package com.example.co.mealapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.co.mealapp.R
import com.example.co.mealapp.Utils.Status
import com.example.co.mealapp.Utils.showIf
import com.example.co.mealapp.dataModels.Category
import com.example.co.mealapp.databinding.CategoriesListBinding
import com.example.co.mealapp.databinding.FragmentCategoriesListBinding
import com.example.co.mealapp.databinding.MainSearchFragmentBinding
import com.example.co.mealapp.ui.adaptor.CategoriesAdapter
import com.example.co.mealapp.ui.viewmodels.MainViewModel


class CategoriesListFragment : Fragment() {
    private lateinit var navController: NavController

    private lateinit var categoriesList: MutableList<Category>
    private lateinit var categoriesAdapter: CategoriesAdapter

    val MViewModel by activityViewModels<MainViewModel>()

    private var _Binding: FragmentCategoriesListBinding? = null
    private val binding get() = _Binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _Binding = FragmentCategoriesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initVars() {
        categoriesList = mutableListOf()
        categoriesAdapter = CategoriesAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVars()
        getCategories()

    }


    //to get category from viewmodel
    private fun getCategories() {
        MViewModel.getCategories().observe(viewLifecycleOwner) {
            binding.Shimmer.showIf {
                it.data == null
            }
            when (it.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    categoriesAdapter.submitList(it.data)
                }

                Status.FAIL -> {
                    Toast.makeText(
                        requireContext(),
                        "failed get data from server",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        initCategoriesRecycler()
    }


    private fun initCategoriesRecycler() {
        binding.categoriesRecycler.apply {
            visibility = View.VISIBLE
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            adapter = categoriesAdapter
        }
    }
}



