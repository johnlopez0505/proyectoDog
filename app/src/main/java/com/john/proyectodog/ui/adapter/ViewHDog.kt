package com.john.proyectodog.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.john.proyectodog.data.models.Dog
import com.john.proyectodog.databinding.ItemDogsBinding



class ViewHDog (view: View): RecyclerView.ViewHolder (view) {

    private lateinit var binding: ItemDogsBinding
    init {
        binding = ItemDogsBinding.bind(view)
    }
    //método que se encarga de mapear los item por propiedad del modelo.
    fun renderize(dog : Dog){
        binding.txtviewName.setText(dog. name)

        Glide
            .with( itemView.context)
            .load(dog.image)
            .centerCrop()
            .into( binding.ivDogs)
    }
}