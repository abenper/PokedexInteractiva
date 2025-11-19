package com.example.composepokedex.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composepokedex.model.PokemonCompose
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

@Composable
// Firma: Recibe los datos del Pokémon (p) y una función de callback para cuando se pulsa (onClick)
fun PokemonRow(p: PokemonCompose, onClick: (PokemonCompose) -> Unit) {
    // Proporciona la elevación y el fondo para el elemento de la lista.
    Card(modifier = Modifier
        .fillMaxWidth() // La tarjeta ocupa todo el ancho disponible
        .padding(8.dp) // Espacio alrededor de la tarjeta
        // .clickable: Hace que toda la tarjeta sea pulsable.
        // Ejecuta onClick con el objeto Pokémon para abrir el diálogo de detalle.
        .clickable { onClick(p) }
    ) {
        // Una fila para organizar los elementos horizontalmente
        Row(modifier = Modifier.padding(8.dp)) {
            // Carga la imagen del Pokémon desde la URL
            AsyncImage(
                model = p.spriteUrl,
                contentDescription = p.name,
                modifier = Modifier.size(56.dp) // Tamaño del sprite en la lista
            )
            // Espacio horizontal entre la imagen y la columna de texto
            Spacer(modifier = Modifier.width(12.dp))
            // Ordena los textos verticalmente (Nombre y Tipo)
            Column {
                // Nombre del Pokémon
                Text(text = p.name)
                Spacer(modifier = Modifier.height(4.dp))
                // Tipo de Pokémon
                Text(text = p.type)
            }
        }
    }
}