package com.example.co.mealapp.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.co.mealapp.R
import com.example.co.mealapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    private lateinit var Listener: NavController.OnDestinationChangedListener

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var drawerLayout: DrawerLayout
    private val listItems= arrayOf("vegetarian","FastFood","Vegan")

    private lateinit var preference:SharedPreferences
    private lateinit var editor:SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.navigateToolbar)

        preference=getSharedPreferences("categoryName",0)
        editor=preference.edit()

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        drawerLayout = binding.appDrawer
        binding.navigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        Listener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                if (destination.id == R.id.Main_Search_Fragment) {
                    binding.navigateToolbar.title = "SearchMeal"
                }
                if (destination.id == R.id.categoriesListFragment) {
                    binding.navigateToolbar.title = "Categories"
                }
                if (destination.id == R.id.Search_Result_Fragment) {
                    binding.navigateToolbar.title = "SearchResult"
                }
                    navigationHeaderOneListener(this)
            }





        binding.bottomNavView.setupWithNavController(navController)


    }

    override fun onPause() {
        super.onPause()
        navController.addOnDestinationChangedListener(Listener)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(Listener)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navcontroller = findNavController(R.id.fragment_container_view)
        return navcontroller.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



    
    private fun navigationHeaderOneListener( context:Context){
        val headerView: View = binding.navigationView.getHeaderView(0)
        headerView.findViewById<TextView?>(R.id.tv_user_name).text=preference.getString("user_Category","NOT_FOUND")
    headerView.findViewById<TextView?>(R.id.tv_user_name).setOnClickListener {
            val dialog=AlertDialog.Builder(this)
                 dialog.apply {
                     setTitle("your Category")
                     setSingleChoiceItems(listItems,listItems.indexOf(preference.getString("user_Category","FastFood"))){dialog,which->
                         editor.putString("user_Category",listItems[which])
                         Toast.makeText(context,"category you chose is : ${listItems[which]}",Toast.LENGTH_LONG).show()
                         editor.apply()
                         }
                     setPositiveButton("Submit",DialogInterface.OnClickListener { dialogInterface, i ->
                         headerView.findViewById<TextView?>(R.id.tv_user_name).text=preference.getString("user_Category","Notfound")
                     })
                     setNegativeButton("Cancel",DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                     })
                     setCancelable(false)
                     create().show()
                 }
           

        }
    }




}