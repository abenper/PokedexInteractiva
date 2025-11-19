package com.example.composepokedex

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.Text
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.composepokedex.ui.screens.GroupedScreen
import com.example.composepokedex.ui.screens.GridScreen
import com.example.composepokedex.ui.screens.ListScreen
import com.example.composepokedex.viewmodel.PokedexViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PokedexApp(vm: PokedexViewModel = viewModel()) {

    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Compose Pokédex") })
        }
    ) { padding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {
            ColumnLayout(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                vm = vm
            )
        }
    }
}


@Composable
private fun ColumnLayout(selectedTab: Int, onTabSelected: (Int) -> Unit, vm: PokedexViewModel) {
    Column {
        TabRow(selectedTabIndex = selectedTab) {
            Tab(selected = selectedTab == 0, onClick = { onTabSelected(0) }) { Text("Lista") }
            Tab(selected = selectedTab == 1, onClick = { onTabSelected(1) }) { Text("Cuadrícula") }
            Tab(selected = selectedTab == 2, onClick = { onTabSelected(2) }) { Text("Por Tipo") }
        }


        when (selectedTab) {
            0 -> ListScreen(vm = vm)
            1 -> GridScreen(vm = vm)
            2 -> GroupedScreen(vm = vm)
        }
    }
}