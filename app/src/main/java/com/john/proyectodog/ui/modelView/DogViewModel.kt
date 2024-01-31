package com.john.proyectodog.ui.modelView

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.proyectodog.data.models.Dog
import com.john.proyectodog.domain.userCase.GetDogsBreedUseCase
import com.john.proyectodog.domain.userCase.GetDogsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DogViewModel: ViewModel() {
    var dogListLiveData = MutableLiveData<List<Dog>>() //repositorio observable.
    var progressBarLiveData = MutableLiveData<Boolean> () //progressbar observable
    var search = MutableLiveData<String>() //para el campo search observable
    lateinit var useCaseList : GetDogsUseCase
    lateinit var useCaseBreedList : GetDogsBreedUseCase

    fun searchByBreed(breed: String){
        Log.i("TAG-DOGS", "La raza elegida es $breed")
        search.value = breed //notificamos cambio
    }
    fun list() {
        viewModelScope.launch { //La corrutina se ejecuta en segundo plano.
            progressBarLiveData.value = true //LiveData notifica del cambio.
            delay(2000)
            useCaseList = GetDogsUseCase()
            var data: List<Dog>? =
                useCaseList() //Invocamos a nuestro caso de uso (lógica de negocio).
            data.let {
                dogListLiveData.value = it //LiveData notifica del cambio.
                // dogListLiveData.postValue(it)
                progressBarLiveData.value = false //LiveData notifica del cambio.
            }
        }
    }
    fun listForBreed(breed: String) {
        viewModelScope.launch {
            progressBarLiveData.value = true //notifico
            delay(2000)
            useCaseBreedList = GetDogsBreedUseCase(breed)
            var data : List<Dog> ? = useCaseBreedList() //Invocamos a nuestro caso de uso (lógica de negocio).
            data.let {
                // dogListLiveData.postValue(it)
                dogListLiveData.value = it //El LiveData notifica el cambio.
                progressBarLiveData.value = false //El LiveData notifica el cambio.
            }
        }
    }
}