package com.example.htmlparsetest.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.htmlparsetest.ProductDetails

val veryLightPink = Color(0xFFEAEAEA)

@Composable
fun SetListView(details: ProductDetails) {
    Column(modifier = Modifier.fillMaxWidth()) {
        details.setList
            .map { CharacteristicsCell(set = it) }
    }
}

@Composable
fun CharacteristicsCell(set: String) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 24.dp)
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "· $set",
                modifier = Modifier.weight(0.6f),
                style = TextStyle(color = Color.Gray)
            )
        }
    }
    Divider(color = veryLightPink)
}