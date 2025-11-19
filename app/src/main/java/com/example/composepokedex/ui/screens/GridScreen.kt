package com.example.composepokedex.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composepokedex.ui.components.PokemonCard
import com.example.composepokedex.viewmodel.PokedexViewModel
import com.example.composepokedex.ui.components.PokemonDetailDialog
import com.example.composepokedex.model.PokemonCompose

@Composable
fun GridScreen(vm: PokedexViewModel) {

    //El inicio de este codigo funciona igual que GroupedScreen.
    //Solo varia el final y eso esta comentado.
    val pokemons by vm.pokemons.collectAsState()
    val loading by vm.loading.collectAsState()

    var selected by remember { mutableStateOf<PokemonCompose?>(null) }

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
                Text("Cargando Pokémons... (Tarda un pelín)")
            }
        }
    } else {
        // Define una cuadrícula con 3 columnas fijas.
        LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxSize()) {
            items(pokemons) { p ->
                // Se utiliza PokemonCard y se actualiza 'selected' al pulsar la tarjeta.
                PokemonCard(p = p, onClick = { selected = it })
            }
        }
    }

    if (selected != null) {
        PokemonDetailDialog(pokemon = selected!!, onClose = { selected = null })
    }
}