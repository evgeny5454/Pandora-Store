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
    val endPoint: String,
    val description: String,
    val status: String
)

data class ProductDetails(
    val productName: String,
    val urlImages: List<String>,
    val setList: List<String>,
    val price: String,
    val description: String,
    val headerImage: String
)

class Repository {
    private val baseurl = "https://www.pandora-alarm.ru"

    private val catalog = "/catalog/dxl/"
    private val html = ".html"
    private val keychain = "/catalog/other/breloki/"
    private val moto = "/catalog/motosignalizacii/"


    fun getData(endPoint: String): List<Product> {
        val doc: Document = Jsoup.connect("$baseurl$endPoint").get()
        val products: Elements = doc.select("div.col-sm-6.col-md-4")
        val listProducts = mutableListOf<Element>()

        products.forEach {
            listProducts.add(it)
        }
        val list = mutableListOf<Product>()
        listProducts.forEach { product ->
            val description = product.select("a.item-hover").text()
            val uri = getUriEndPoint(product, endPoint)
            val image = product.select("img").attr("src")
            val nameProduct = product.select("div.priceProducts").select("a").text()
            val priceProduct = product.select("div.priceProducts").select("span").first()?.text()
            val statusProduct = product.select("span.statusProducts.pull-right").text()

            Log.d("ProductUrl", uri)

            val productItem = Product(
                title = nameProduct,
                imageUrl = "$baseurl$image",
                price = priceProduct ?: "",
                endPoint = uri,
                status = statusProduct,
                description = description
            )

            list.add(productItem)
        }
        Log.d("ListItems", list.toString())
        return list
    }

    private fun getUriEndPoint(product: Element, endPoint: String): String {
        val uri = product.select("a.item-hover").attr("href")
        val formatted = uri.replace(endPoint, "")
        return formatted.replace(html, "")
    }


    fun getDataFromLink(link: String, endPoint: String, price: Boolean): ProductDetails {

        val doc: Document = Jsoup.connect("$baseurl$link$endPoint$html").get()
        val headerData: Elements = doc.select("div.col-md-9.col-md-push-3")
        val allDetails: Elements = doc.select("div.tab-content")

        val urlImages = getImages(headerData = headerData).toMutableList()
        val headerImage = urlImages.first()
        urlImages -= headerImage

        val productDetails = ProductDetails(
            productName = getProductName(headerData = headerData),
            urlImages = urlImages,
            price = if (price) getPrice(headerData = headerData) else "",
            setList = if (price
                && link == catalog
            )
                getSetList(allDetails = allDetails) else emptyList(),
            description = getDescription(allDetails = allDetails),
            headerImage = headerImage
        )

        Log.d("productDetails", productDetails.toString())
        return productDetails
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
        val textRegex2 = "BT-780 - 1 шт., BT-760 - 1 шт."
        val textRegex3 = "Данное видео снималось"
        val textRegex4 = "При оформлении заказа"
        val textRegex5 = "Как определить серийный номер"

        val description = allDetails.select("div").select("p")

        val listDescription = mutableListOf<String>()
        for (index in description.indices) {
            val item = description[index].text()
            if (item.isNotEmpty()
                && item.length > 29
                && !item.contains(textRegex)
                && !item.contains(textRegex2)
                && !item.contains(textRegex3)
                && !item.contains(textRegex4)
                && !item.contains(textRegex5)
            )
                listDescription.add(item)
        }
        for (i in 1..2) {
            listDescription.removeLast()
        }
        var descriptionText = ""
        listDescription.forEach {
            descriptionText = "$descriptionText\n\n$it"
        }
        Log.d("descriptionText", descriptionText)
        return descriptionText
    }

    fun addProductInCart(item: Product, cart: List<Product>): List<Product> {
        val cartList: MutableList<Product> = mutableListOf()
        cartList.addAll(cart)
        cartList.add(item)


        //getSumPrise(priceList)

        return cartList
    }

    fun getSumPrise(item: Product, cart: List<Product>): String {
        var int = 0
        val priceList: MutableList<String> = mutableListOf()
        cart.forEach { product ->
            priceList.add(product.price)
        }
        priceList.add(item.price)

        priceList.forEach {
            val format = it.replace(" ", "")
            int += format.toInt()
        }
        return setDelimiter("$int")
    }
}

fun setDelimiter(string: String) : String{
    var formattedString = string
    val pointPosition = formattedString.lastIndex + 1
    formattedString = formattedString.substring(0, pointPosition - 3) + " " + formattedString.substring(pointPosition - 3)
    for (i in 0 until pointPosition / 3) {
        val lastSpacePosition = formattedString.indexOf(" ")
        formattedString = if (lastSpacePosition - 3 > 0) {
            formattedString.substring(
                0,
                lastSpacePosition - 3
            ) + " " + formattedString.substring(lastSpacePosition - 3)
        } else {
            break
        }
    }
    return formattedString
}