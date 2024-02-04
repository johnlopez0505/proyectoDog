package com.john.proyectodog.data.models

import com.john.proyectodog.data.service.DogService
import javax.inject.Inject

class DogRepositoryDao @Inject constructor(
        private val service: DogService
    ) : DogRepositoryInterfaceDao {

    /*
    Método que a partir de los datos nativos, devuelve la lista
    de objetos que necesita el modelo.
    */
    override fun getDogs(): List<Dog> {
        var mutableDogs : MutableList <Dog> = mutableListOf()

        val dataSource = service.getDogs()
        dataSource .forEach{ dog->
            mutableDogs .add(Dog(dog. first, dog.second))
        }
        Repository .dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return Repository .dogs
    }

    override fun getBreedDogs(breed: String): List<Dog> {
        var mutableDogs : MutableList <Dog> = mutableListOf()

        val dataSource = service.getBreedDogs(breed)
        dataSource .forEach{ dog->
            mutableDogs .add(Dog(dog. first, dog.second))
        }
        Repository .dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return Repository .dogs
    }

}