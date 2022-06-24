package com.example.appapipokemon.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RoutesApi {

    @GET("pokemon/")
    suspend fun listPokemons(
        @Query("limit") limit: Int
    ): Response<PokemonResponse>
}