package com.example.htmlparsetest.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.htmlparsetest.MainViewModel
import com.example.htmlparsetest.ProductDetails

@Composable
fun PriceView(mainViewModel: MainViewModel, possible: Boolean){
    val detailsProduct by mainViewModel.detailsProduct.observeAsState(
        ProductDetails(
            "",
            emptyList(), emptyList(), "", "",""
        )
    )
    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = detailsProduct.price,
            modifier = Modifier.padding(start = 16.dp),
            style = TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Medium)
        )
        /*Text(
            text = "₽/шт.",
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
            style = TextStyle(color = Color.LightGray, fontSize = 12.sp)
        )*/
        Spacer(modifier = Modifier.weight(1f))
        if (possible) CardButton(mainViewModel = mainViewModel)

    }
}