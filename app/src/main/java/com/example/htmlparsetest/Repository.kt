package com.example.htmlparsetest

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

data class Product(
    val title: String,
    val imageUrl: String,
    val price: String,
    val productUrl: String,
    val description: String,
    val status: String
)

data class ProductDetails(
    val productName: String,
    val urlImages: List<String>,
    val setList: List<String>,
    val price: String,
    val description: String
)

class Repository {
    private val baseurl = "https://www.pandora-alarm.ru"
    private val catalog = "catalog/dxl/"


    fun getData(): List<Product> {
        val doc: Document = Jsoup.connect("https://www.pandora-alarm.ru/catalog/dxl/").get()
        val products: Elements = doc.select("div.col-sm-6.col-md-4")
        val listProducts = mutableListOf<Element>()

        products.forEach {
            listProducts.add(it)
        }
        val list = mutableListOf<Product>()
        listProducts.forEach { product ->
            val description = product.select("a.item-hover").text()
            val uri = product.select("a.item-hover").attr("href")
            val image = product.select("img").attr("src")
            val nameProduct = product.select("div.priceProducts").select("a").text()
            val priceProduct = product.select("div.priceProducts").select("span").first()?.text()
            val statusProduct = product.select("span.statusProducts.pull-right").text()

            val productItem = Product(
                title = nameProduct,
                imageUrl = "$baseurl$image",
                price = priceProduct ?: "",
                productUrl = "$baseurl$uri",
                status = statusProduct,
                description = description
            )
            list.add(productItem)
        }
        Log.d("list", list.toString())
        return list
    }


    fun getDataFromLink(url: String) {
        val doc: Document = Jsoup.connect(url).get()
        val headerData: Elements = doc.select("div.col-md-9.col-md-push-3")
        val allDetails: Elements = doc.select("div.tab-content")

        val productDetails = ProductDetails(
            productName = getProductName(headerData = headerData),
            urlImages = getImages(headerData = headerData),
            setList = getSetList(allDetails = allDetails),
            price = getPrice(headerData = headerData),
            description = getDescription(allDetails = allDetails)
        )

        Log.d("productDetails", productDetails.toString())
    }

    private fun getPrice(headerData: Elements): String {
        return headerData.select("div.b-product-card__price-value")[0].text()
    }

    private fun getProductName(headerData: Elements): String {
        return headerData.select("h1.product-title").text()
    }

    private fun getImages(headerData: Elements): List<String> {
        val uriImages = headerData.select("div.fotorama").select("img")
        val listImages = mutableListOf<String>()
        uriImages.forEach {
            val uri = (it.select("img").attr("data-thumb").toString())
            listImages.add("$baseurl$uri")
        }
        return listImages
    }

    private fun getSetList(allDetails: Elements): List<String> {
        val set = allDetails.select("ul")[4].select("li")
        val setList = mutableListOf<String>()
        set.forEach {
            setList.add(it.text())
        }
        return setList
    }

    private fun getDescription(allDetails: Elements): String {
        val textRegex = "Реализация функции автоматического и дистанционного запуска двигателя"

        val description = allDetails.select("div").select("p")

        val listDescription = mutableListOf<String>()
        for (index in description.indices) {
            val item = description[index].text()
            if (item.isNotEmpty() && item.length > 29 && !item.contains(textRegex)
            )
                listDescription.add(item)
        }
        for (i in 1..2) {
            listDescription.removeLast()
        }
        var descriptionText = ""
        listDescription.forEach {
            descriptionText = "$descriptionText\n$it"
        }
        return descriptionText
    }
}