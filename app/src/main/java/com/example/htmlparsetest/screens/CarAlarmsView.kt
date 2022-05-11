package com.example.htmlparsetest.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.htmlparsetest.navigatoin_drawer.NavDrawerItem
import com.example.htmlparsetest.view_model.MainViewModel
import com.example.htmlparsetest.views.ItemsList
import com.example.htmlparsetest.views.TopBar
import kotlinx.coroutines.CoroutineScope

private const val catalog = "/catalog/dxl/"

@Composable
fun CarAlarmsView(
    mainViewModel: MainViewModel,
    navController: NavController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    mainViewModel.getData(catalog)
    val listProduct by mainViewModel.listProduct.observeAsState(emptyList())
    Scaffold(
        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState) }
    ) {
        ItemsList(
            productsList = listProduct,
            mainViewModel = mainViewModel,
            navController = navController
        )
    }


}