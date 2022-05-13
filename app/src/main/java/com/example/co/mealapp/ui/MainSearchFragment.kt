package com.example.co.mealapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.co.mealapp.R
import com.example.co.mealapp.Utils.Status
import com.example.co.mealapp.Utils.showIf
import com.example.co.mealapp.dataModels.Category
import com.example.co.mealapp.databinding.MainSearchFragmentBinding
import com.example.co.mealapp.ui.adaptor.CategoriesAdapter
import com.example.co.mealapp.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainSearchFragment : Fragment() {

     private var _Binding:MainSearchFragmentBinding?=null
     private val binding get() = _Binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      _Binding= MainSearchFragmentBinding.inflate(inflater,container,false)
        val view= binding.root

            binding.btnGoCategoriesList.setOnClickListener {

                val action=Navigation.findNavController(view)
                    .navigate(R.id.action_Main_Search_Fragment_to_categoriesListFragment)
            }



        return view

    }


}