package com.example.composepokedex.model

import com.squareup.moshi.Json

data class PokemonRespuestaLista(
    val count: Int,
    val results: List<Recurso>
)

data class Recurso(
    val name: String,
    val url: String
)

data class PokemonDetalles(
    val id: Int,
    val name: String,
    val types: List<PokemonTipo>,
    val abilities: List<PokemonHabilidad>
)

data class PokemonTipo(
    val slot: Int,
    val type: Recurso
)

data class PokemonHabilidad(
    val slot: Int,
    val ability: Recurso,
    @Json(name = "is_hidden") val isHidden: Boolean
)

//El resto se usa para transformar lo que la api nos devuelve en json
// ya que las descripciones vienen anidadas

//Este es el objeto final que usan los componentes compose
data class PokemonCompose(
    val id: Int,
    val name: String,
    val type: String,
    val spriteUrl: String?,
    val description: String = ""
)