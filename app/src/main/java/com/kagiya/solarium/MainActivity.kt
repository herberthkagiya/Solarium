package com.kagiya.solarium

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.ar.core.Config
import com.kagiya.solarium.databinding.ActivityMainBinding
import com.kagiya.solarium.ui.theme.SolariumTheme
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSceneView()
        setupPlanetsNodes()
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

    private fun setupPlanetsNodes() {

        val solarSystemNode = ArModelNode(
            binding.arSceneView.engine,
            PlacementMode.INSTANT
        ).apply {
            binding.arSceneView.planeRenderer.isVisible = false
            isSmoothPoseEnable = true
            followHitPosition = true
            loadModelGlbAsync(
                glbFileLocation = "models/Earth.glb",
                autoAnimate = true,
                scaleToUnits = 2.0f,
                centerOrigin = Position(0.0f, 0.0f, 3.0f)
            )
        }


        binding.arSceneView.addChild(solarSystemNode)
    }
}
