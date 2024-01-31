package com.john.proyectodog.domain.userCase

import com.john.proyectodog.data.models.Dog
import com.john.proyectodog.data.models.DogRepositoryDao

class GetDogsUseCase {
    operator fun invoke(): List<Dog>?{
        return  DogRepositoryDao.myDogRepositoryDao.getDogs()
    }
}