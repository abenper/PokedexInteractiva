package com.example.composepokedex.data

import com.example.composepokedex.model.PokemonCompose
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class Repository(private val api: ApiService = ModuloRed.api) {

    // Funciones que usan cosas de red que no se sabe cuanto tiempo van a tardar en ejecutarse
    // usamos suspend, ya que así no paramos el hilo principal
    suspend fun loadPokemons(onProgress: (Int) -> Unit): List<PokemonCompose> = withContext(Dispatchers.IO) {

        // Obtener la lista general (nombre y URL)
        val listResp = api.getListaPokemons(500)
        val pokemonsList = mutableListOf<PokemonCompose>()

        //Iterar sobre la lista para obtener el detalle de cada Pokémon
        for (r in listResp.results) {
            try {
                // Obtener el detalle completo (PokemonDetail)
                val d = api.getDetallesPokemons(r.url)

                // Extraer y formatear el tipo de pokemon
                val mainType = d.types.firstOrNull()?.type?.name ?: "Error"
                val id = d.id

                // URL de la imagen:
                val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

                // Descripción del pokemon
                val description = if (d.abilities.isNotEmpty()) {
                    // Usar habilidades si las tiene
                    "Habilidades: ${d.abilities.joinToString {
                        it.ability.name.replaceFirstChar { c -> c.uppercase() }
                    }}"
                } else {
                    // En caso de no obtener las habilidades, error
                    "Error Habilidades"
                }

                // Añadir a la lista final (PokemonCompose)
                pokemonsList.add(
                    PokemonCompose(
                        id = id,
                        name = d.name.replaceFirstChar { it.uppercase() },
                        type = mainType.replaceFirstChar { it.uppercase() },
                        spriteUrl = imageUrl,
                        description = description
                    )
                )

                onProgress(r. + 1)

            } catch (e: Exception) {
                //Ya que nos conectamos a una api lo hacemos con catch para controlar si hay errores
            }
        }

        // Devolver la lista ordenada por ID
        pokemonsList.sortedBy { it.id }
    }
}