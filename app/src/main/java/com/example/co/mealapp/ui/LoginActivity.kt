package com.example.co.mealapp.ui

import android.app.Dialog
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.co.mealapp.R
import com.example.co.mealapp.Utils.showSnack
import com.example.co.mealapp.Utils.showToast
import com.example.co.mealapp.databinding.ActivityLoginBinding
import com.example.co.mealapp.databinding.SigninDialogLayoutBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    //user bioFinger
    private var cancelationsignal: CancellationSignal?=null
    private val AutenticationCallback: BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object :BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    showToast("error $errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    val intent= Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    showSnack(binding.root,"welcome!!",Snackbar.LENGTH_LONG)
                }

            }
    //binding
    private lateinit var binding:ActivityLoginBinding
    //user&passwordPreference
    private lateinit var userPref:SharedPreferences
    private var isLogin:Boolean=false
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CheckedBiometricsupport()
        makeUserFinger()
        binding.btnUseFinger.setOnClickListener {
           makeUserFinger()
        }
        userPref=getSharedPreferences("userConfiguration",0)
        binding.tvUserName.text=userPref.getString("userConfigName","USERNAME_NOT_FOUND")
        binding.btnUseSignInDialog.setOnClickListener {
            signIn()
        }
        loginOptions()

    }

    //fingerprint
    private fun CheckedBiometricsupport() : Boolean {
        val keyguardManager: KeyguardManager =getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isDeviceSecure){
            showToast("your device dose not have finger print")
            return false
        }
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.USE_BIOMETRIC)
            != PackageManager.PERMISSION_GRANTED){
            showToast("finger print is not enable")
            return false
        }
        return if(packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            true
        } else true
    }
    private fun getcancelationsignal(): CancellationSignal {
        cancelationsignal= CancellationSignal()
        cancelationsignal?.setOnCancelListener {
            showToast("your fingerprint is canceld by user")
        }
        return cancelationsignal as CancellationSignal
    }
    @RequiresApi(Build.VERSION_CODES.P)
    private fun makeUserFinger(){
        val biometricPrompt: BiometricPrompt = BiometricPrompt.Builder(this)
            .setTitle("FingerPrint")
            .setTitle("your security is needed")
            .setDescription("this app is use the finger print")
            .setNegativeButton(
                "cancel",
                this.mainExecutor,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    showToast("Cancel")
                }).build()
        biometricPrompt.authenticate(getcancelationsignal(),mainExecutor,AutenticationCallback)
    }

    //singIN
    private fun signIn(){
        val editor:SharedPreferences.Editor=userPref.edit()
        val dialogBinding:SigninDialogLayoutBinding= SigninDialogLayoutBinding.inflate(layoutInflater)
        val dialog:Dialog= Dialog(this).apply {
            setContentView(dialogBinding.root)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialogBinding.btnSubmit.setOnClickListener {
                if (dialogBinding.inputUserName.text.isEmpty() || dialogBinding.inputPassword.text.isEmpty()) {
                    showSnack(dialogBinding.root, "please enter your user and passWord")

                } else {
                    editor.putString("userConfigName", dialogBinding.inputUserName.text.toString())
                    editor.putString("passWord", dialogBinding.inputPassword.text.toString())
                    isLogin = true
                    editor.putBoolean("isLogin", isLogin)
                    editor.apply()
                    binding.signCard.visibility = View.GONE
                    binding.tvUserName.text = userPref.getString("userConfigName", "USER_NOT_FOUND")
                    dismiss()
                }
            }
            show()

        }



    }

    private fun loginOptions(){
        isLogin=userPref.getBoolean("isLogin",false)
        if (isLogin) binding.signCard.visibility=View.GONE

        binding.btnSubmit.setOnClickListener {
            if (binding.inputLoginPassword.text.isEmpty()){
                binding.inputLoginPassword.setError("please enter your password")
                binding.btnSubmit.isEnabled=false
            }
            else{
                 if (binding.inputLoginPassword.text.toString()==userPref.getString("passWord","NOT_FOUND")){
                     val intent=Intent(this,MainActivity::class.java)
                     startActivity(intent)
                     showSnack(binding.root,"welcome !!!")
                 }
                else{
                    showSnack(binding.root,"your password is incorrect")
                 }

            }
        }
    }
}