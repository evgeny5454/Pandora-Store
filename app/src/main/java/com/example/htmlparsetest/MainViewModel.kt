package com.example.htmlparsetest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val repository = Repository()

    private val _listProduct: MutableLiveData<List<Product>> = MutableLiveData()
    val listProduct: LiveData<List<Product>> = _listProduct

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(IO) {
           _listProduct.postValue(repository.getData())
        }
    }
}

