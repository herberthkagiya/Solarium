package com.kagiya.solarium.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kagiya.solarium.R
import com.kagiya.solarium.databinding.FragmentArBinding
import com.kagiya.solarium.databinding.FragmentOnboardingBinding


class OnboardingFragment : Fragment() {

    private lateinit var  binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getStartedButton.setOnClickListener{
            setAlreadSawOnboarding()
            findNavController().popBackStack()
        }
    }


    companion object{
        const val COMPLETED_ONBOARDING_PREF_NAME = "COMPLETED_ONBOARDING_PREF"
    }


    private fun setAlreadSawOnboarding(){
        val sharedPref = activity?.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return

        with (sharedPref.edit()) {
            putBoolean(COMPLETED_ONBOARDING_PREF_NAME, false)
            apply()
        }
    }
}