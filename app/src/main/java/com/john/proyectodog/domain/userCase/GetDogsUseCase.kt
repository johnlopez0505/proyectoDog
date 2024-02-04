package com.john.proyectodog.domain.userCase

import com.john.proyectodog.data.models.Dog
import com.john.proyectodog.data.models.DogRepositoryDao
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(
        private  val dogRepositoryDao: DogRepositoryDao) {
    operator fun invoke(): List<Dog>?{
        return  dogRepositoryDao.getDogs()
    }
}