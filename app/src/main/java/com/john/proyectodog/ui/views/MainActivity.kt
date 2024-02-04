package com.john.proyectodog.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.john.proyectodog.databinding.ActivityMainBinding
import com.john.proyectodog.ui.adapter.DogAdapter
import com.john.proyectodog.ui.modelView.DogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: DogAdapter
    val dogViewModel: DogViewModel by viewModels() //tiene que ser constante.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater)
        setContentView(binding.root)

        binding.mySearch.setOnQueryTextListener( this) //cargamos el listener, para el EditText
        initRecyclerView() //inicializamos el recyclerView. De memento, contendrá lista empty.
        adapter = DogAdapter() // Inicializa el adaptador con una lista vacía
        registerLiveData() //Suscribimos nuestro Activity a recibir notificaciones del ViewModel.
        loadDada()
    }

    private fun initRecyclerView(){
        binding.myRecyclerView.layoutManager = LinearLayoutManager( this)
    }

    private fun loadDada() {
        dogViewModel.list() //simulamos un evento para iniciar la carga de datos desde el viewmodel
    }

    private fun registerLiveData() {
        dogViewModel.dogListLiveData.observe(
            this
        ) { myList ->
            //Aquí hacemos la actualización del adapter.
            adapter.dogRepository = myList!! //aseguro los datos.
            binding.myRecyclerView.adapter = adapter //le asigno el adapter.
            adapter.notifyDataSetChanged()
        }




        dogViewModel.progressBarLiveData.observe(
            this
        ) { visible ->
            binding.progressBar.isVisible = visible
            //Log.i("TAG-DOGS", "ProgressBar esta $visible")
        }
        /*
        Observamos un cambio en el search. De la misma forma, será notificado y actualizará la UI.
        */
        dogViewModel.search.observe( //el campo search, ha cambiado
            this
        ) { bread ->
            dogViewModel.listForBreed(bread) //cambiamos los datos.
            hideKeyBoard() //Este método oculta el teclado.
        }

        dogViewModel.text.observe(
            this
        ){it ->
            binding.textDog.text = it
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query. isNullOrEmpty())
            dogViewModel.searchByBreed(query!!)
        return true
    }
    /*
    Cualquier cambio, llamará a este método. Estoy obligado a sobreescribirlo.
    Lo utilizo para cargar toda la lista de nuevo, al
    estar el campo vacío. Es lo que se me ha ocurrido para borrar la raza y que los cargue todos.
    */
    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText. isNullOrEmpty()) {
            dogViewModel.list()
            hideKeyBoard() //esconde el teclado.
        }
        return true
    }

    private fun hideKeyBoard() {
        val imn = getSystemService( INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow( binding.myRecyclerView .windowToken, 0)
    }

}