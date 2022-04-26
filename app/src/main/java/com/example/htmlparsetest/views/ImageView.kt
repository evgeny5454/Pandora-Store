package com.example.htmlparsetest.views

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ImageView(
    imageUri: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        GlideImage(
            imageModel = imageUri,
            contentScale = ContentScale.FillWidth,
            modifier = modifier
        )
    }
}