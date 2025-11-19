package com.example.composepokedex.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composepokedex.model.PokemonCompose

@Composable
//Recibe el objeto Pokémon y una función para cerrar el diálogo
fun PokemonDetailDialog(pokemon: PokemonCompose, onClose: () -> Unit) {
    // AlertDialog el contenedor modal principal
    AlertDialog(
        // onDismissRequest cierra el diálogo si el usuario toca fuera o usa el botón de atrás
        onDismissRequest = onClose,
        shape = RoundedCornerShape(16.dp), // Esquinas redondeadas

        //Contenido principal del diálogo
        text = {
            //Row para ordenar la imagen y texto
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically // Centra verticalmente los elementos de la fila
            ) {
                // Muestra la imagen ampliada del Pokémon
                AsyncImage(
                    model = pokemon.spriteUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier
                        .size(120.dp) // Tamaño ampliado
                        .padding(end = 12.dp)
                )

                // Organiza la información del texto verticalmente
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f) // Hace que la columna ocupe el espacio restante
                ) {
                    // Nombre del Pokémon
                    Text(
                        text = pokemon.name,
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Tipo principal
                    Text(
                        text = "Tipo: ${pokemon.type}",
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // Descripción o Habilidades (campo description)
                    Text(
                        text = pokemon.description,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        },

        // Área para las acciones del diálogo
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center // Centra los botones en la fila
            ) {
                // Botón Ver en Pokedex
                Button(onClick = {}) {
                    Icon(Icons.Default.OpenInNew, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Ver en Pokédex")
                }
                Spacer(modifier = Modifier.width(12.dp))
                // Botón principal de cierre
                Button(onClick = onClose) { // Ejecuta la función de cierre
                    Text("Cerrar")
                }
            }
        }
    )
}
