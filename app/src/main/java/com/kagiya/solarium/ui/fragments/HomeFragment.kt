package com.kagiya.solarium.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kagiya.solarium.R
import com.kagiya.solarium.adapters.PlanetsAdapter
import com.kagiya.solarium.data.PlanetsRepository
import com.kagiya.solarium.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val repository = PlanetsRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.planetsRecyclerView.layoutManager = GridLayoutManager(context, 2)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val planets = repository.getPlanets()

        binding.planetsRecyclerView.adapter = PlanetsAdapter(planets){ planetName ->
            findNavController().navigate(
                HomeFragmentDirections.showPlanetAR(planetName)
            )
        }
    }

}