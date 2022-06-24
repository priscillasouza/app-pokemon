package com.example.appapipokemon.api

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Boolean,
    @SerializedName("results")
    val results: List<Pokemon>
)

data class Pokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
