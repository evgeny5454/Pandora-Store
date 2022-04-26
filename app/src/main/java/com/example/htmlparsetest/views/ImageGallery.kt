package com.example.htmlparsetest.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ImageGallery(listImages: List<String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(listImages) { uri ->
            ImageView(imageUri = uri)
        }
    }
}