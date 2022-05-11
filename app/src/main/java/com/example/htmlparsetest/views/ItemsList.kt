package com.example.htmlparsetest.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.htmlparsetest.Product
import com.example.htmlparsetest.view_model.MainViewModel

@Composable
fun ItemsList(productsList: List<Product>, mainViewModel: MainViewModel, navController: NavController){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(productsList) { product ->
            ItemButton(mainViewModel = mainViewModel , product = product , navController = navController)
        }
    }

}