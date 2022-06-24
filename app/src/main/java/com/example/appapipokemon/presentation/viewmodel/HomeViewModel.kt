package com.example.appapipokemon.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appapipokemon.api.PokemonResponse
import com.example.appapipokemon.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var pokemonRepository: PokemonRepository = PokemonRepository()

    private var _pokemonsResponse: MutableLiveData<PokemonResponse> = MutableLiveData()
    val pokemonResponse: LiveData<PokemonResponse> = _pokemonsResponse

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun listPokemons(limit: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.listPokemon(limit).catch { exception ->
                _error.postValue(exception.message)
            }.collect {
                _pokemonsResponse.postValue(it)
            }
        }
    }
}