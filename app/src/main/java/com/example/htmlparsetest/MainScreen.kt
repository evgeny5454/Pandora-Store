package com.example.htmlparsetest

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.htmlparsetest.views.*

private const val catalog = "/catalog/dxl/"
private const val keychain = "/catalog/other/breloki/"
private const val moto = "/catalog/motosignalizacii/"
private const val gps = "/catalog/gpsmayaki/"
private const val accessories = "/catalog/other/"
private const val cart = "cart"


@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {

    val listProduct by mainViewModel.listProduct.observeAsState(emptyList())
    val bottomItems = listOf(catalog, accessories, cart, moto, gps)
    val navPoint by mainViewModel.navPoint.observeAsState("")
    //var showDialog by remember { mutableStateOf(false) }

    val navControllerHost = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavigation {
            bottomItems.forEach { point ->
                BottomNavigationItem(
                    selected = false,
                    onClick = {
                        if (point != cart) {
                            mainViewModel.getData(point = point)
                        } else {
                            navControllerHost.navigate(point)
                        }
                    },
                    label = {
                        when (point) {
                            catalog -> {
                                Text(text = "Авто")
                            }
                            keychain -> {
                                Text(text = "Брелки")
                            }
                            moto -> {
                                Text(text = "Мото")
                            }
                            gps -> {
                                Text(text = "Маяки")
                            }
                            accessories -> {
                                Text(text = "Аксесуар")
                            }
                            cart -> {
                                Text(text = "Козина")
                            }
                        }
                    },
                    icon = {
                        when (point) {
                            catalog -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_auto),
                                    contentDescription = null
                                )
                            }
                            keychain -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_keychain),
                                    contentDescription = null
                                )
                            }
                            moto -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_moto),
                                    contentDescription = null
                                )
                            }
                            gps -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_gps),
                                    contentDescription = null
                                )
                            }
                            cart -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_shopping_cart),
                                    contentDescription = null
                                )
                            }
                            accessories -> {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_accessories),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            }
        }
    }) {
        NavHost(
            navController = navControllerHost,
            startDestination = accessories,
            modifier = Modifier.padding(it)
        ) {
            composable(catalog) {
                ProductList(
                    productsList = listProduct,
                    navController,
                    mainViewModel
                )
            }
            composable(keychain) {
                ProductList(
                    productsList = listProduct,
                    navController,
                    mainViewModel
                )
            }
            composable(moto) {
                ProductList(
                    productsList = listProduct,
                    navController,
                    mainViewModel
                )
            }
            composable(gps) {
                ProductList(
                    productsList = listProduct,
                    navController,
                    mainViewModel
                )
            }
            composable(accessories) {
                ProductList(
                    productsList = listProduct,
                    navController,
                    mainViewModel
                )
            }
            composable(cart) {
                Cart(mainViewModel = mainViewModel)
            }
        }
    }
}

@Composable
fun ProductList(
    productsList: List<Product>,
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val link by mainViewModel.navPoint.observeAsState("")
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    )
    {
        if (link != cart) {
            items(productsList) { product ->
                if (link == accessories) {
                    ItemButton2(
                        mainViewModel = mainViewModel,
                        product = product,
                        link = link
                    )
                } else {
                    ItemButton(
                        product = product,
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }

}

