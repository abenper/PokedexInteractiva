package com.example.composepokedex.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composepokedex.ui.components.PokemonRow
import com.example.composepokedex.viewmodel.PokedexViewModel
import com.example.composepokedex.ui.components.PokemonDetailDialog
import com.example.composepokedex.model.PokemonCompose



@Composable
fun ListScreen(vm: PokedexViewModel) {

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
                Text("Cargando Pokémons... (Tarda un pelin)")
            }
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            // items. Dibuja una fila (PokemonRow) para cada Pokémon en la lista.
            items(pokemons) { p ->
                // Al pulsar la fila, se actualiza 'selected' para abrir el diálogo.
                PokemonRow(p = p, onClick = { selected = it })
            }
        }
    }

    if (selected != null) {
        PokemonDetailDialog(pokemon = selected!!, onClose = { selected = null })
    }
}