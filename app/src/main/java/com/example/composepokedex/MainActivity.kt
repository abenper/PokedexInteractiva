package com.example.composepokedex


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composepokedex.ui.theme.ComposePokedexTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePokedexTheme {
                //Llamamos a App.kt
                PokedexApp()
            }
        }
    }
}