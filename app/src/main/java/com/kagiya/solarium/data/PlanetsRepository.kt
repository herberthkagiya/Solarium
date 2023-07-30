package com.kagiya.solarium.data

import com.kagiya.solarium.R

class PlanetsRepository {

    fun getPlanets() : List<Planet>{
        return listOf(
            Planet(
                R.string.mercury_name,
                R.string.mercury_description,
                "#e1d9fa",
                R.drawable.mercury,
                "models/Mercury.glb"
            ),

            Planet(
                R.string.venus_name,
                R.string.venus_description,
                "#feb51c",
                R.drawable.venus,
                "models/Venus.glb"
            ),

            Planet(
                R.string.earth_name,
                R.string.earth_description,
                "#B6F3FF",
                R.drawable.earth,
                "models/Earth.glb"
            ),

            Planet(
                R.string.mars_name,
                R.string.mars_description,
                "#f56488",
                R.drawable.mars,
                "models/Mars.glb"
            ),

            Planet(
                R.string.jupter_name,
                R.string.jupter_description,
                "#f4d2a9",
                R.drawable.jupter,
                "models/Jupter.glb"
            ),

            Planet(
                R.string.saturn_name,
                R.string.saturn_description,
                "#c6dbaa",
                R.drawable.saturn,
                "models/Saturn.glb"
            ),

            Planet(
                R.string.uranus_name,
                R.string.uranus_description,
                "#bba0ef",
                R.drawable.uranus,
                "models/Uranus.glb"
            ),

            Planet(
                R.string.neptune_name,
                R.string.neptune_description,
                "#20a8ee",
                R.drawable.neptune,
                "models/Uranus.glb"
            )
        )
    }
}

data class Planet(
    val name: Int,
    val description: Int,
    val cardBackgroundColor: String,
    val image: Int,
    val modelLocation: String,
)