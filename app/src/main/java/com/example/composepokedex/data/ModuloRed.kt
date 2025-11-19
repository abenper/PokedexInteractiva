package com.example.composepokedex.data

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object ModuloRed {

    // URL Base de la api
    private const val BASE = "https://pokeapi.co/api/v2/"

    // Creamos una instancia de Moshi,
    // una librería de Kotlin utilizada para convertir el texto JSON
    // que viene de la API en objetos
    private val moshi = Moshi.Builder().build()

    // Esto lo usamos para que registre en el logcat las solicitudes
    // a la api para que podamos ver que ocurre
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    // Este es el encargado de manejar las peticiones
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()

    // Creamos la instancia final de NetworkModule
    val api: ApiService = Retrofit.Builder()
        .baseUrl(BASE)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ApiService::class.java)
}