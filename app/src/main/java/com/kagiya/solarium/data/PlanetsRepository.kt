package com.kagiya.solarium.data

import com.kagiya.solarium.R

class PlanetsRepository {

    fun getPlanets() : List<Planet>{

        return listOf(
            Planet(
                R.string.solar_system_name,
                R.string.solar_system_description,
                "#fcee77",
                R.drawable.solar_system,
                "models/Solar_System.glb",
                2.5f
            ),

            Planet(
                R.string.mercury_name,
                R.string.mercury_description,
                "#e1d9fa",
                R.drawable.mercury,
                "models/Mercury.glb",
                1.0f
            ),

            Planet(
                R.string.venus_name,
                R.string.venus_description,
                "#feb51c",
                R.drawable.venus,
                "models/Venus.glb",
                1.0f
            ),

            Planet(
                R.string.earth_name,
                R.string.earth_description,
                "#B6F3FF",
                R.drawable.earth,
                "models/Earth.glb",
                1.0f
            ),

            Planet(
                R.string.mars_name,
                R.string.mars_description,
                "#f56488",
                R.drawable.mars,
                "models/Mars.glb",
                1.0f
            ),

            Planet(
                R.string.jupter_name,
                R.string.jupter_description,
                "#f4d2a9",
                R.drawable.jupter,
                "models/Jupter.glb",
                1.0f
            ),

            Planet(
                R.string.saturn_name,
                R.string.saturn_description,
                "#c6dbaa",
                R.drawable.saturn,
                "models/Saturn.glb",
                1.0f
            ),

            Planet(
                R.string.uranus_name,
                R.string.uranus_description,
                "#bba0ef",
                R.drawable.uranus,
                "models/Uranus.glb",
                1.0f
            ),

            Planet(
                R.string.neptune_name,
                R.string.neptune_description,
                "#20a8ee",
                R.drawable.neptune,
                "models/Neptune.glb",
                 1.0f
            )
        )
    }

    fun getPlanetByName(planetName: Int) : Planet?{
        val allPlanets = getPlanets()

        val planet = allPlanets.find {
            it.name == planetName
        }

        return planet
    }
}

data class Planet(
    val name: Int,
    val description: Int,
    val cardBackgroundColor: String,
    val image: Int,
    val modelLocation: String,
    val scaleSize: Float
)