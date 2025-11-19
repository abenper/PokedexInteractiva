package com.example.composepokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepokedex.data.Repository
import com.example.composepokedex.model.PokemonCompose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokedexViewModel(private val repo: Repository = Repository()) : ViewModel() {

    // 1. Estado de los datos (lo que ve la UI)
    // _pokemons: Es mutable (se puede cambiar) y privado.
    private val _pokemons = MutableStateFlow<List<PokemonCompose>>(emptyList())
    // pokemons: Es inmutable (solo lectura) y público. La UI lo observa.
    val pokemons: StateFlow<List<PokemonCompose>> = _pokemons


    // 2. Estado de la carga (el spinner)
    // _loading: Es mutable y privado. Inicia en 'true' para mostrar el spinner al principio.
    private val _loading = MutableStateFlow(true)
    // loading: Es inmutable y público. La UI lo observa para saber cuándo mostrar el spinner.
    val loading: StateFlow<Boolean> = _loading

    private val _pokemonsLoadedCount = MutableStateFlow(0)
    val pokemonsLoadedCount: StateFlow<Int> = _pokemonsLoadedCount

    // init: Se ejecuta inmediatamente cuando se crea la instancia del ViewModel.
    init {
        cargarPokemons() // Inicia la carga de datos al arrancar la vista.
    }

    // Función de carga de datos (lógica de negocio)
    private fun cargarPokemons() {
        // Inicia una corrutina
        viewModelScope.launch {
            _loading.value = true //Muestra la rueda de carga
            _pokemonsLoadedCount.value = 0

            _pokemons.value = try {
                // Llama al Repository para hacer el trabajo de red (I/O).
                // La corrutina se suspende aquí hasta que loadPokemons() devuelve el resultado de ahí que sea suspend fun.
                repo.loadPokemons()
            } catch (e: Exception) {
                //Manejo de errores. Si falla vacia la lista.
                emptyList()
            }

            _loading.value = false //Oculta el spinner
        }
    }
}