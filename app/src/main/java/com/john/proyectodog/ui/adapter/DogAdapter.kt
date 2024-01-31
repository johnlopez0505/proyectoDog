package com.john.proyectodog.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.john.proyectodog.R
import com.john.proyectodog.data.models.Dog
import com.john.proyectodog.data.models.Repository

class DogAdapter: RecyclerView.Adapter<ViewHDog>(){

    var dogRepository: List<Dog> = Repository. dogs //cargo del repsitorio dememoria.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHDog {
        val layoutInflater = LayoutInflater.from(parent. context)//objeto para crear la vista.
        val layoutItemRestaurant = R.layout.item_dogs //accedo al xml del item a crear.
        return ViewHDog(
            layoutInflater.inflate(layoutItemRestaurant, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHDog, position: Int) {
        holder.renderize(dogRepository[position]) //renderizamos la view.
    }

    override fun getItemCount(): Int = dogRepository.size
}