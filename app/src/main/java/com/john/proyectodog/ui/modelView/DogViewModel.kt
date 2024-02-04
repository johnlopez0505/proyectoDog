package com.john.proyectodog.ui.modelView


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.proyectodog.data.models.Dog
import com.john.proyectodog.domain.userCase.GetDogsBreedUseCase
import com.john.proyectodog.domain.userCase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class DogViewModel @Inject constructor(
    private val useCaseList : GetDogsUseCase,
    private val getDogsBreedUseCaseProvider: Provider<GetDogsBreedUseCase>
)  : ViewModel() {
    var dogListLiveData = MutableLiveData<List<Dog>>() //repositorio observable.
    var progressBarLiveData = MutableLiveData<Boolean> () //progressbar observable
    var search = MutableLiveData<String>() //para el campo search observable
    val text = MutableLiveData<String>()

    fun searchByBreed(breed: String){
        search.value = breed //se notifican los cambios
    }
    fun list() {
        viewModelScope.launch { //La corrutina se ejecuta en segundo plano.
            progressBarLiveData.value = true //LiveData notifica del cambio.
            delay(2000)
            var data: List<Dog>? =
                useCaseList() //Invocamos a nuestro caso de uso (lógica de negocio).
            data.let {
                dogListLiveData.value = it //LiveData notifica del cambio.
                text.value = ""
                // dogListLiveData.postValue(it)
                progressBarLiveData.value = false //LiveData notifica del cambio.
            }
        }
    }
    fun listForBreed(breed: String) {
        viewModelScope.launch {
            progressBarLiveData.value = true //notifico
            delay(2000)
            //useCaseBreedList = GetDogsBreedUseCase(breed)
            val useCaseBreedList = getDogsBreedUseCaseProvider.get()
            useCaseBreedList.setBreed(breed)
            var data : List<Dog> ? = useCaseBreedList() //Invocamos a nuestro caso de uso (lógica de negocio).
            data.let {
                // dogListLiveData.postValue(it)
                dogListLiveData.value = it //El LiveData notifica el cambio.
                if(data!!.isEmpty())
                    text.value = "No se ha encontrado el perro en la lista"
                progressBarLiveData.value = false //El LiveData notifica el cambio.
            }
        }
    }
}