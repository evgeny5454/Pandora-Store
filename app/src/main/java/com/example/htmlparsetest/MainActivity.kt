package com.example.htmlparsetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.htmlparsetest.navigation.NavigationTrue
import com.example.htmlparsetest.view_model.MainViewModel
import com.example.htmlparsetest.views.TopBar
import com.example.navdrawercompose.Drawer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel()

    Scaffold(
        scaffoldState = scaffoldState,
        /*topBar = { TopBar(scope = scope, scaffoldState = scaffoldState)},*/
        drawerBackgroundColor = Color.LightGray,
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController )
        },
    ) {
        NavigationTrue(navController = navController, mainViewModel = mainViewModel,scope = scope, scaffoldState = scaffoldState)
    }
}
