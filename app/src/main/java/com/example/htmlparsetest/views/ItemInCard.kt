package com.example.htmlparsetest.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.htmlparsetest.Product

@Composable
fun ItemInCard(
    product: ItemCounter
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .padding(4.dp)
        .clickable {
            /*val possible : Boolean = product.status == IsPossible
            mainViewModel.setPossible(possible)
            mainViewModel.setPrise(product.price)
            mainViewModel.setProduct(product)
            navController.navigate(Screen.DetailsScreen.withArgs(product.endPoint))*/
        }) {
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, color = Color.Black),
            elevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageView(
                    imageUri = product.image,
                    modifier = Modifier
                        .size(100.dp)
                )
                SubtitleMedium4(text = product.title, modifier = Modifier.weight(0.6f))
                Caption(text = "${product.count} шт.", color = Color.Gray, modifier = Modifier.weight(0.2f))
                Caption(text = product.prise, color = Color.Black)
            }
        }
    }
}