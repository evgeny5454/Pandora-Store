package com.example.htmlparsetest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.htmlparsetest.views.*


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsScreen(mainViewModel: MainViewModel, endPoint: String?, navController: NavController) {
    if (endPoint != null) {
        mainViewModel.getDataFromLink(endPoint)
    }
    val detailsProduct by mainViewModel.detailsProduct.observeAsState(
        ProductDetails(
            "",
            emptyList(), emptyList(), "", "", ""
        )
    )
    val possible by mainViewModel.possible.observeAsState(false)

    LazyColumn(content = {
        stickyHeader {
            Toolbar(details = detailsProduct, navController = navController)
        }
        if (detailsProduct.urlImages.isNotEmpty()) {
            item { ImageGallery(listImages = detailsProduct.urlImages) }
        } else {
            item { ImageGallery(listImages = listOf(detailsProduct.headerImage)) }
        }
        if (detailsProduct.price.isNotEmpty()){
            item { PriceView(mainViewModel = mainViewModel, possible = possible) }
        }
        item { HeaderView(height = 68.dp, title = "Описание") }
        item {
            SubtitleMedium4(
                text = detailsProduct.description,
                modifier = Modifier.padding(start = 16.dp, end = 24.dp)
            )
        }
        if (detailsProduct.setList.isNotEmpty()) {
            item { HeaderView(height = 68.dp, title = "Комплектация") }
            item { SetListView(details = detailsProduct) }
        }
    })
}

