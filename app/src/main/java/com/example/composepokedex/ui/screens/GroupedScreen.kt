package com.example.composepokedex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.composepokedex.model.PokemonCompose
import com.example.composepokedex.ui.components.PokemonRow
import com.example.composepokedex.ui.components.PokemonDetailDialog
import com.example.composepokedex.viewmodel.PokedexViewModel

@Composable
fun GroupedScreen(vm: PokedexViewModel) {
    // Obtiene los pokemons y el estado de carga desde el viewmodel
    val pokemons by vm.pokemons.collectAsState()
    val loading by vm.loading.collectAsState()

    // Estado de UI. Utiliza 'remember' para mantener el Pokémon seleccionado a través de las recomposiciones,
    // lo cual controla la visibilidad del diálogo.
    var selected by remember { mutableStateOf<PokemonCompose?>(null) }

    // Lógica condicional si 'loading' es verdadero, se muestra el indicador de progreso.
    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(modifier = Modifier.size(64.dp))
                Spacer(modifier = Modifier.height(16.dp))
                androidx.compose.material3.Text("Cargando Pokémons... (Tarda un pelin)")
            }
        }
    } else {
        // Se agrupan los Pokémon por su campo 'type' para crear secciones.
        val grouped = pokemons.groupBy { it.type }

        // LazyColumn contiene la lista vertical.
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            // Se itera sobre cada tipo de Pokémon.
            grouped.forEach { (type, list) ->
                // stickyHeader ya que lo pide el ejercicio
                // al desplazar la lista, indicando el tipo.
                stickyHeader {
                    val headerColor = colorForType(type)
                    Text(
                        text = type,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(headerColor) // Se aplica el color de fondo temático.
                    )
                }
                // Items dibuja una fila (PokemonRow) para cada Pokémon dentro del grupo.
                items(list) { p ->
                    // Al pulsar la fila, se actualiza la variable 'selected'.
                    PokemonRow(p = p, onClick = { selected = it })
                }
            }
        }
    }

    // Se muestra solo si 'selected' no es nulo.
    if (selected != null) {
        // Se llama al diálogo y se proporciona la acción para cerrarlo, reseteando 'selected' a null.
        PokemonDetailDialog(pokemon = selected!!, onClose = { selected = null })
    }
}

fun colorForType(type: String): Color {
    return when (type.lowercase()) {
        "fire" -> Color(0xFFEE8130) // Fuego
        "water" -> Color(0xFF6390F0) // Agua
        "grass" -> Color(0xFF7AC74C) // Planta
        "electric" -> Color(0xFFF7D02C) // Eléctrico
        "psychic" -> Color(0xFFF95587) // Psíquico
        "ice" -> Color(0xFF96D9D6) // Hielo
        "dragon" -> Color(0xFF6F35FC) // Dragón
        "dark" -> Color(0xFF705746) // Siniestro
        "fairy" -> Color(0xFFD685AD) // Hada
        else -> Color.LightGray // Por defecto
    }
}