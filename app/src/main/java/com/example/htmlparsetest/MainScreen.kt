package com.example.htmlparsetest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val listProduct by mainViewModel.listProduct.observeAsState(emptyList())

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(listProduct) { product ->
            ProductItem(product = product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Column {
        ImageView(imageUri = product.imageUrl, modifier = Modifier.size(200.dp))
        Text(text = product.title)
        Text(text = product.price)
    }
}