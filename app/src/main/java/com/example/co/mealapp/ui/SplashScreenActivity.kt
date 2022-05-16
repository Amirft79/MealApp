package com.example.co.mealapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.co.mealapp.Utils.NetworkHelper
import com.example.co.mealapp.Utils.showSnack
import com.example.co.mealapp.Utils.showToast
import com.example.co.mealapp.databinding.ActivitySplashScreenBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            if (NetworkHelper.isNetworkConnected(this@SplashScreenActivity)){
                startApp()
            }
            else{
                showSnack(binding.root,"Check your Internet Connection",5000)
                binding.btnCheckInternet.visibility=View.VISIBLE
                binding.btnCheckInternet.setOnClickListener {
                    if (NetworkHelper.isNetworkConnected(this@SplashScreenActivity)){
                        startApp()
                    }
                    else{
                        showSnack(binding.root,"Check your Internet Connection",5000)
                    }
                }
            }
        }


    }
    private fun startApp(){

            binding.ivCucktus.alpha = 0f
            binding.ivCucktus.animate().setDuration(4000).alpha(1f).withEndAction {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }

    }
}