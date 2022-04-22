package com.example.htmlparsetest.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.htmlparsetest.ProductDetails
import com.example.htmlparsetest.R

@Composable
fun Toolbar(details: ProductDetails, navController: NavController){
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = R.drawable.ic_back),
            contentDescription = "Back button",
            modifier = Modifier
                .clickable {
                    navController.popBackStack()
                }
                .size(56.dp)
                .padding(16.dp)
        )
        ImageView(imageUri = details.headerImage, modifier = Modifier.fillMaxHeight().width(56.dp))
        Subtitle5(text = details.productName, modifier = Modifier.weight(1f))

        Image(
            painterResource(id = R.drawable.ic_more),
            contentDescription = "Back more",
            modifier = Modifier
                .clickable {

                }
                .size(56.dp)
                .padding(16.dp)
        )
    }
}