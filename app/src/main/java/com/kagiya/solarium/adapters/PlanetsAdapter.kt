package com.kagiya.solarium.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kagiya.solarium.data.Planet
import com.kagiya.solarium.data.PlanetsRepository
import com.kagiya.solarium.databinding.ListItemPlanetBinding


class PlanetsAdapter(
    private val planets: List<Planet>
) : RecyclerView.Adapter<PlanetViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPlanetBinding.inflate(inflater, parent, false)
        return PlanetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.bind(planets[position])
    }

    override fun getItemCount(): Int {
        return planets.size
    }
}


class PlanetViewHolder (
    private val binding : ListItemPlanetBinding
): RecyclerView.ViewHolder(binding.root){

    fun bind(planet: Planet){

        binding.card.background.setTint(Color.parseColor(planet.cardBackgroundColor))
        binding.planetImage.setImageResource(planet.image)
        binding.planetName.setText(planet.name)
        binding.planetDescription.setText(planet.description)
    }
}
