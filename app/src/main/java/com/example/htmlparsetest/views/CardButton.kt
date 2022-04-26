package com.example.htmlparsetest.views

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.htmlparsetest.Product
import com.example.htmlparsetest.view_model.MainViewModel

//data class SelectedProduct(val name: String, val prise: String, val headerImage: String)

@Composable
fun CardButton(mainViewModel: MainViewModel) {
    val cart by mainViewModel.cart.observeAsState(emptyList())
    val setProduct by mainViewModel.setProduct.observeAsState(Product("", "", "", "", "", ""))

    Button(
        onClick = {
            mainViewModel.addToCart()
        },
        modifier = Modifier
            .height(48.dp)
            .width(160.dp)
            .padding(end = 24.dp)
    ) {
        TextButton(cart, setProduct)
    }
}

@Composable
fun TextButton(cart: List<Product>, setProduct: Product) {
    var counter = 0
    if (cart.contains(setProduct)) {
        cart.forEach {
            if (it == setProduct) {
                counter++
            }
        }
    }
    val text: String = if (counter > 0) "$counter" else "В корзину"
    Text(
        text = text,
        style = TextStyle(
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
    )
}