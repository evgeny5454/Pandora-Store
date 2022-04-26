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
import androidx.navigation.NavController
import com.example.htmlparsetest.Product
import com.example.htmlparsetest.Screen
import com.example.htmlparsetest.view_model.MainViewModel

const val IsPossible = "Есть в наличии"

@Composable
fun ItemButton(
    mainViewModel: MainViewModel,
    product: Product,
    navController: NavController
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .padding(4.dp)
        .clickable {
            val possible: Boolean = product.status == IsPossible
            mainViewModel.setPossible(possible)
            mainViewModel.setPrise(product.price)
            mainViewModel.setProduct(product)
            navController.navigate(Screen.DetailsScreen.withArgs(product.endPoint))
        }) {
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, color = Color.Black),
            elevation = 0.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageView(
                    imageUri = product.imageUrl,
                    modifier = Modifier
                        .width(300.dp)
                )

                Subtitle5(text = product.title)
                Spacer(modifier = Modifier.height(4.dp))
                SubtitleMedium4(text = product.description)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SubtitleMedium4(
                        text = if (product.price.isNotEmpty()) "${product.price} ₽" else "Уточнить цену",
                        modifier = Modifier.weight(1f)
                    )
                    Caption(
                        text = product.status,
                        color = if (product.status == IsPossible) Color.Green else Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ItemButton2(
    mainViewModel: MainViewModel,
    product: Product,
    link: String
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .padding(4.dp)
        .clickable {
            mainViewModel.getData("$link${product.endPoint}")
        }) {
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, color = Color.Black),
            elevation = 0.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageView(
                    imageUri = product.imageUrl,
                    modifier = Modifier
                        .width(300.dp)
                )
                Subtitle5(text = product.title)
                Spacer(modifier = Modifier.height(4.dp))
                SubtitleMedium4(text = product.description)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}