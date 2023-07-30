package com.kagiya.solarium.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.kagiya.solarium.R
import com.kagiya.solarium.databinding.ActivityMainBinding
import com.kagiya.solarium.ui.fragments.OnboardingFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnboardingIfNecessary()
    }


    private fun setOnboardingIfNecessary(){
        if(isFirstTimeLaunchingTheApp()){
            showOnboardingScreen()
        }
    }

    private fun isFirstTimeLaunchingTheApp() : Boolean{
        val sharedPref = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        return sharedPref.getBoolean(OnboardingFragment.COMPLETED_ONBOARDING_PREF_NAME, true)
    }

    private fun showOnboardingScreen(){
        val navController = supportFragmentManager.findFragmentById(R.id.fragment_container)?.findNavController()

        navController?.navigate(R.id.onboardingFragment)
    }

}
