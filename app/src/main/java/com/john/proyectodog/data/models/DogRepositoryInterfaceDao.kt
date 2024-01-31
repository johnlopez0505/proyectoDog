package com.john.proyectodog.data.models

interface DogRepositoryInterfaceDao {
    fun getDogs() : List<Dog>
    fun getBreedDogs (breed:String) : List<Dog>
}