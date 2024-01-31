package com.john.proyectodog.data.models

import com.john.proyectodog.data.service.DogService

class DogRepositoryDao : DogRepositoryInterfaceDao {

    companion object {
        val myDogRepositoryDao: DogRepositoryDao by lazy{ //lazy delega a un primer acceso
            DogRepositoryDao() //Me creo sólo este objeto una vez.
        }
    }


    /*
    Método que a partir de los datos nativos, devuelve la lista
    de objetos que necesita el modelo.
    */
    override fun getDogs(): List<Dog> {
        var mutableDogs : MutableList <Dog> = mutableListOf()

        val dataSource = DogService.service.getDogs()
        dataSource .forEach{ dog->
            mutableDogs .add(Dog(dog. first, dog.second))
        }
        Repository .dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return Repository .dogs
    }

    override fun getBreedDogs(breed: String): List<Dog> {
        var mutableDogs : MutableList <Dog> = mutableListOf()

        val dataSource = DogService.service.getBreedDogs(breed)
        dataSource .forEach{ dog->
            mutableDogs .add(Dog(dog. first, dog.second))
        }
        Repository .dogs = mutableDogs //AQUÍ CARGO LOS DATOS EN MEMORIA.
        return Repository .dogs
    }

}