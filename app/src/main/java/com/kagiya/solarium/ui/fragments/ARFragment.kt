package com.kagiya.solarium.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.ar.core.Anchor
import com.google.ar.core.Config
import com.google.ar.core.HitResult
import com.google.ar.core.TrackingState
import com.kagiya.solarium.R
import com.kagiya.solarium.data.PlanetsRepository
import com.kagiya.solarium.databinding.FragmentArBinding
import io.github.sceneview.ar.arcore.createAnchor
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.ArNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation

class ARFragment : Fragment() {

    private lateinit var binding: FragmentArBinding
    private val repository = PlanetsRepository()
    private val args: ARFragmentArgs by navArgs()
    private lateinit var modelNode: ArModelNode
    lateinit var earthAnchor: Anchor

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
        setupBackButton()
        setupAnchorFAB()
    }


    private fun setupSceneView(){

        binding.arSceneView.apply {
            lightEstimationMode = Config.LightEstimationMode.DISABLED
            onArSessionFailed = { e: Exception ->
                // If AR is not available or the camara permission has been denied, we add the model
                // directly to the scene for a fallback 3D only usage
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
            onTapAr = { hitResult, _ ->
            }

            cloudAnchorEnabled = true
        }
    }

    private fun setupPlanetNode() {

        val planet = repository.getPlanetByName(args.planetName)


        modelNode = ArModelNode(
            binding.arSceneView.engine,
            PlacementMode.INSTANT,
        ).apply {

            parent = binding.arSceneView
            isSmoothPoseEnable = true


            loadModelGlbAsync(
                glbFileLocation = planet!!.modelLocation,
                scaleToUnits = 2.0f * planet.scaleSize,
                centerOrigin = Position(z = 4.0f),

            ){
                binding.arSceneView.planeRenderer.isVisible = false
            }

            onAnchorChanged = {
                binding.anchorFAB.isGone = it != null


                modelNode.onAnchorChanged = { anchor: Anchor? ->

                }

            }
        }


        binding.arSceneView.addChild(modelNode)
    }

    private fun setupBackButton(){
        //Disable back button
        requireActivity().onBackPressedDispatcher.addCallback(this) {
        }

        binding.backButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun setupAnchorFAB(){

        binding.anchorFAB.setOnClickListener{

            modelNode.anchor()
        }
    }

    fun anchorOrMove(anchor: Anchor) {
        if (modelNode == null) {
            modelNode = ArModelNode(
                binding.arSceneView.engine,
                followHitPosition = false
            ).apply {
                modelInstance = modelInstance
                parent = binding.arSceneView
            }
        }
        modelNode.anchor = anchor
    }
}