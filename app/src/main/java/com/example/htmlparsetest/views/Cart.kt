package com.example.htmlparsetest.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import com.example.htmlparsetest.MainViewModel
import com.example.htmlparsetest.Product

data class ItemCounter(val title: String, val count: Int, val prise: String, val image: String)

@Composable
fun Cart(mainViewModel: MainViewModel) {
    //var count = 0
    val listCart by mainViewModel.cart.observeAsState(emptyList())

    //val list = mutableListOf<Product>()


    val list = countItems(listCart)

    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {

        items(list) { item ->
            ItemInCard(product = item)
        }
    }
}

fun countItems(listCart: List<Product>): List<ItemCounter> {
    var count = 0
    val list = mutableListOf<Product>()
    val listOfItems = mutableListOf<ItemCounter>()
    val sortedlist = listCart.sortedBy { it.title }

    sortedlist.forEach { item ->
        if (!list.contains(item)) {
            list.add(item)
            count = 1
            listOfItems.add(ItemCounter(item.title, count = count, prise = item.price, item.imageUrl))
        } else {
            listOfItems.remove(ItemCounter(item.title, count = count, prise = item.price, item.imageUrl))
            count++
            listOfItems.add(ItemCounter(item.title, count = count, prise = item.price, item.imageUrl))
        }
    }

    Log.d("COUNTER", listOfItems.toString())
    return listOfItems

}
