package com.example.htmlparsetest

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val listProduct by mainViewModel.listProduct.observeAsState(emptyList())
    ProductGreed(productsList = listProduct)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductGreed(productsList: List<Product>) {
    LazyColumn(
        //cells = GridCells.Adaptive(minSize = 200.dp),
        modifier = Modifier.padding(4.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)


    )
    {
        items(productsList) { product ->
            ProductItem(product = product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .padding(4.dp)
        .clickable{

        }) {
        Card(modifier = Modifier
                .border(width = 1.dp, color = Color.Black),
            elevation = 0.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            Column() {
                ImageView(
                    imageUri = product.imageUrl, modifier = Modifier
                        //.border(width = 1.dp, color = Color.Black)
                        .height(150.dp)
                        .fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        //.border(width = 1.dp, color = Color.Black)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Subtitle5(
                            text = product.title, modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp)
                        )
                        Caption(
                            text = product.price,
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        )

                    }
                }
            }
        }
    }
}