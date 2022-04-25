package com.example.htmlparsetest.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.htmlparsetest.MainViewModel
import com.example.htmlparsetest.Product

@Composable
fun ItemInCard(
    product: Product,
    count: Int,
    mainViewModel: MainViewModel
) {
    var showDialog by remember { mutableStateOf(false) }

    ShowDialog(
        mainViewModel = mainViewModel, showDialog,
        onClick = {
            showDialog = it
        },
        product = product,
        count = count
    )

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .padding(4.dp)
        .clickable {
            showDialog = !showDialog
        }
    ) {
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
                    imageUri = product.imageUrl,
                    modifier = Modifier
                        .size(100.dp)
                )
                SubtitleMedium4(text = product.title, modifier = Modifier.weight(0.6f))
                Caption(
                    text = "$count шт.",
                    color = Color.Gray,
                    modifier = Modifier.weight(0.2f)
                )
                Caption(text = product.price, color = Color.Black)

            }
        }
    }
}

@Composable
fun ShowDialog(
    mainViewModel: MainViewModel,
    showDialog: Boolean,
    onClick: (Boolean) -> Unit,
    product: Product,
    count: Int
) {
    if (showDialog) {
        MyDialog(onDismiss = {
            onClick.invoke(!showDialog)
        }, onNegativeClick = { myProduct ->
            onClick.invoke(!showDialog)
            mainViewModel.deleteProduct(myProduct)
        }, onPositiveClick = { myCount, myProduct ->
            onClick.invoke(!showDialog)
            mainViewModel.correctСount(count = myCount, product = myProduct)
        }, product = product, count = count)
    }
}