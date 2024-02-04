package com.john.proyectodog.data.service

import com.john.proyectodog.data.dataSorce.Dogs
import javax.inject.Inject
class DogService @Inject constructor(): DogServiceInterface {

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