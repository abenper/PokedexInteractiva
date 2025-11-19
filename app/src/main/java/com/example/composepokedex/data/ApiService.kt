package com.example.composepokedex.data

import com.example.composepokedex.model.PokemonDetalles
import com.example.composepokedex.model.PokemonRespuestaLista
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("pokemon")
    suspend fun getListaPokemons(@Query("limit") limit: Int = 0): PokemonRespuestaLista


    @GET
    suspend fun getDetallesPokemons(@Url url: String): PokemonDetalles
}