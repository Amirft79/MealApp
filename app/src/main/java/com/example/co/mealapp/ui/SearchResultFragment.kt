package com.example.co.mealapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.co.mealapp.R
import com.example.co.mealapp.Utils.*
import com.example.co.mealapp.databinding.FragmentSearchResultBinding
import com.example.co.mealapp.databinding.MainSearchFragmentBinding
import com.example.co.mealapp.ui.adaptor.SearchedMealsAdapter
import com.example.co.mealapp.ui.viewmodels.MainViewModel

class SearchResultFragment : Fragment() {


    private var _binding:FragmentSearchResultBinding?=null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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



    }


}