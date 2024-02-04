package com.john.proyectodog.domain.userCase

import com.john.proyectodog.data.models.Dog
import com.john.proyectodog.data.models.DogRepositoryDao
import javax.inject.Inject

class GetDogsBreedUseCase @Inject constructor(
    private val dogRepositoryDao: DogRepositoryDao
    ){

    private var breed: String = ""

    fun setBreed(breed: String){
        this.breed = breed
    }
    operator fun invoke() : List<Dog>{
        return  dogRepositoryDao.getBreedDogs(breed)
    }
}