package com.example.composepokedex.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composepokedex.model.PokemonCompose

@Composable
fun PokemonCard(p: PokemonCompose, onClick: (PokemonCompose) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp) // Espacio de separación alrededor de cada tarjeta
            // Al tocar, ejecuta la función onClick con el Pokémon actual
            .clickable { onClick(p) }
    ) {
        // Con column ordenamos la imagen y el texto verticalmente
        Column(
            modifier = Modifier.size(120.dp), // Fija un tamaño de 120x120 dp para la tarjeta
            // Centra todos los elementos horizontalmente (imagen y texto)
            horizontalAlignment = Alignment.CenterHorizontally,
            // Centra el contenido verticalmente dentro del espacio de la tarjeta
            verticalArrangement = Arrangement.Center
        ) {
            // Carga la imagen del Pokémon
            AsyncImage(
                model = p.spriteUrl, // La URL de la imagen obtenida del modelo
                contentDescription = p.name, // Texto para accesibilidad (lectores de pantalla)
                modifier = Modifier.size(72.dp) // Tamaño del sprite
            )
            // Separador espacio vertical entre la imagen y el texto
            Spacer(modifier = Modifier.height(8.dp))
            // Muestra el nombre del Pokémon
            Text(text = p.name)
        }
    }
}