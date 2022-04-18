package com.example.htmlparsetest

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

data class Product(val title: String, val imageUrl: String, val price: String)

class Repository {


    fun getData() :List<Product> {
        val baseurl = "https://www.pandora-alarm.ru/"
        val doc: Document = Jsoup.connect(baseurl).get()
        Log.d("TIIITLE", doc.title())
        val products: Elements = doc.select("div.products-list__wrapper ")
        val productsSize = products.size
        Log.d("PRODUCTS", productsSize.toString())

        val list = mutableListOf<Product>()
        products.forEach {
            val title =
                it.select("div.products-list__item-header.col-xs-12.order-3.order-md-2").text()
            val imageUrl = it.select("div.products-list__item-image").select("img").attr("src")
            val price = it.select("div.products-list__item-price.products-list__item-price--color-red").text()
            list.add(Product(title = title, imageUrl = "https://www.pandora-alarm.ru$imageUrl", price = price))
        }
        Log.d("PRODUCTS", list.toString())
        return list
    }
}