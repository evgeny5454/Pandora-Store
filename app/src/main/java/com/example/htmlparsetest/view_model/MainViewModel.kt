package com.example.htmlparsetest.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.htmlparsetest.Product
import com.example.htmlparsetest.ProductDetails
import com.example.htmlparsetest.Repository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val catalog = "/catalog/dxl/"
private const val cartPoint = "cart"

class MainViewModel : ViewModel() {
    private val repository = Repository()

    private val _listProduct: MutableLiveData<List<Product>> = MutableLiveData()
    val listProduct: LiveData<List<Product>> = _listProduct

    private val _setProduct: MutableLiveData<Product> = MutableLiveData()
    val setProduct: LiveData<Product> = _setProduct

    private val _cart: MutableLiveData<List<Product>> = MutableLiveData()
    val cart: LiveData<List<Product>> = _cart

    private val _detailsProduct: MutableLiveData<ProductDetails> = MutableLiveData()
    val detailsProduct: LiveData<ProductDetails> = _detailsProduct

    private val _finalPrise: MutableLiveData<String> = MutableLiveData()
    val finalPrise: LiveData<String> = _finalPrise

    private val _prise: MutableLiveData<Boolean> = MutableLiveData()
    //val prise: LiveData<Boolean> = _prise

    private val _possible: MutableLiveData<Boolean> = MutableLiveData()
    val possible: LiveData<Boolean> = _possible

    private val _navPoint: MutableLiveData<String> = MutableLiveData(catalog)
    val navPoint: LiveData<String> = _navPoint

    init {
        getData(catalog)
    }

    fun getData(point: String) {
        _navPoint.postValue(point)
        if (point != cartPoint) {
            viewModelScope.launch(IO) {
                _listProduct.postValue(repository.getData(point))
            }
        }
    }

    fun setPrise(prise: String) {
        if (prise.isNotEmpty()) {
            _prise.postValue(true)
        } else {
            _prise.postValue(false)
        }
    }

    fun getDataFromLink(endPoint: String) {
        //val url = "https://www.pandora-alarm.ru/catalog/dxl/pandect-x-1800-l-v2.html"
        viewModelScope.launch(IO) {
            _navPoint.value?.let { point ->
                _detailsProduct.postValue(
                    repository.getDataFromLink(
                        point,
                        endPoint,
                        _prise.value ?: false
                    )
                )
            }

        }
    }

    fun addToCart() {
        viewModelScope.launch(IO) {
            var item = Product("", "", "", "", "", "")
            _setProduct.value?.let {
                item = it
            }
            var cart = emptyList<Product>()
            _cart.value?.let {
                cart = it
            }
            _cart.postValue(repository.addProductInCart(item, cart))
            val string = repository.getSumPrise(item, cart)
            _finalPrise.postValue("$string ₽")
        }
    }

    fun setPossible(possible: Boolean) {
        _possible.postValue(possible)
    }

    fun setProduct(product: Product) {
        _setProduct.postValue(product)
    }

    fun correctСount(count: Int, product: Product) {
        var cart = emptyList<Product>()
        _cart.value?.let {
            cart = it
        }
        _cart.value = repository.correctCount(count, product, cart)
        updateFinalPrise()
    }

    private fun updateFinalPrise() {
        var cart = emptyList<Product>()
        _cart.value?.let {
            cart = it
        }
        if (cart.isNotEmpty()) {
            _finalPrise.postValue(repository.getSumPrise(cart))
        } else {
            _finalPrise.postValue("")
        }
    }

    fun deleteProduct(myProduct: Product) {
        var cart = emptyList<Product>()
        _cart.value?.let {
            cart = it
        }
        _cart.value = repository.deleteProduct(myProduct, cart)

        updateFinalPrise()


    }
}

