package com.kagiya.solarium.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.google.ar.core.Config
import com.kagiya.solarium.data.Planet
import com.kagiya.solarium.data.PlanetsRepository
import com.kagiya.solarium.databinding.FragmentArBinding
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position

class ARFragment : Fragment() {

    private lateinit var binding: FragmentArBinding
    private val repository = PlanetsRepository()
    private val args: ARFragmentArgs by navArgs()
    private lateinit var solarSystemNode: ArModelNode

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSceneView()
        setupPlanetNode()
    }



    private fun setupSceneView(){

        binding.arSceneView.apply {
            lightEstimationMode = Config.LightEstimationMode.ENVIRONMENTAL_HDR
            onArSessionFailed = { e: Exception ->
                // If AR is not available or the camara permission has been denied, we add the model
                // directly to the scene for a fallback 3D only usage
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupPlanetNode() {

        val planet = getPlanet()

        solarSystemNode = ArModelNode(
            binding.arSceneView.engine,
            PlacementMode.INSTANT
        ).apply {
            binding.arSceneView.planeRenderer.isVisible = false
            isSmoothPoseEnable = true
            followHitPosition = true
            loadModelGlbAsync(
                glbFileLocation = planet!!.modelLocation,
                autoAnimate = true,
                scaleToUnits = 2.0f * planet.scaleSize,
                centerOrigin = Position(0.0f, 0.0f, 2.0f)
            )
        }

        binding.arSceneView.addChild(solarSystemNode)
    }

    private fun getPlanet() : Planet?{
        val allPlanets = repository.getPlanets()
        val planetName = args.planetName

        val planet = allPlanets.find {
            it.name == planetName
        }

       return planet
    }
}