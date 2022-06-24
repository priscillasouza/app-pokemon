package com.example.appapipokemon.repository

import com.example.appapipokemon.api.Api
import com.example.appapipokemon.api.PokemonResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class PokemonRepository {

    private var routesApi = Api("https://pokeapi.co/api/v2/").create()

    private val DEFAULT_LIMITE = 151

    suspend fun listPokemon(
        limit: Int? = null
    ): Flow<PokemonResponse> {
        return flow {
            routesApi.listPokemons(
                limit?: DEFAULT_LIMITE
            ).let { response ->
                if (response.isSuccessful) {
                    response.body()
                }else {
                    throw HttpException(response)
                }
            }?.let {
                emit(it)
            }
        }
    }
}