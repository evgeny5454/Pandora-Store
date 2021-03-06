package com.example.htmlparsetest.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.htmlparsetest.Product
import com.example.htmlparsetest.view_model.MainViewModel
import com.example.htmlparsetest.views.ItemInCard
import com.example.htmlparsetest.views.Subtitle5

data class ItemCounter(val title: String, val count: Int, val prise: String, val image: String)


@Composable
fun Cart(mainViewModel: MainViewModel) {
    val listCart by mainViewModel.cart.observeAsState(emptyList())
    val finalPrise by mainViewModel.finalPrise.observeAsState("")
    val list = countItems(listCart)

    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {

        list.forEach { (product, count) ->
            item { ItemInCard(product = product, count = count, mainViewModel = mainViewModel) }
        }

        item {
            Box(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Subtitle5(text = "Итоговая стоимость:", modifier = Modifier.weight(0.6f))
                    Subtitle5(text = finalPrise, modifier = Modifier.weight(0.4f))
                }
            }
        }
    }

}


fun countItems(listCart: List<Product>): Map<Product, Int> {
    var count = 0
    val listProduct = mutableListOf<Product>()
    val listOfItems = mutableListOf<ItemCounter>()
    val sortedlist = listCart.sortedBy { it.title }

    sortedlist.forEach { item ->
        if (!listProduct.contains(item)) {
            count = 1
            listOfItems.add(
                ItemCounter(
                    item.title,
                    count = count,
                    prise = item.price,
                    item.imageUrl
                )
            )
            listProduct.add(item)
        } else {
            listOfItems.remove(
                ItemCounter(
                    item.title,
                    count = count,
                    prise = item.price,
                    item.imageUrl
                )
            )
            count++
            listOfItems.add(
                ItemCounter(
                    item.title,
                    count = count,
                    prise = item.price,
                    item.imageUrl
                )
            )
        }
    }
    val finishMap = mutableMapOf<Product, Int>()
    listProduct.forEach { product ->
        listOfItems.forEach { itemCounter ->
            if (product.title == itemCounter.title) {
                finishMap[product] = itemCounter.count
            }
        }
    }

    Log.d("COUNTERRRRR", finishMap.toString())
    // listOfItems
    return finishMap
}


