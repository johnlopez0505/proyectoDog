package com.john.proyectodog.data.service

import com.john.proyectodog.data.dataSorce.Dogs

class DogService : DogServiceInterface {

    companion object {
        val service: DogService by lazy{ //lazy delega a un primer acceso
            DogService() //Me creo sólo este objeto una vez.
        }
    }
    //Método que accede a la BBDD y devuelve todos los datos
    override fun getDogs(): List<Pair<String, String>> {
        return Dogs.dogs
    }

    override fun getBreedDogs(breed: String): List<Pair<String, String>> {
        val newDogs = Dogs.dogs.filter {
            it.first == breed
        }
        return newDogs
    }
}