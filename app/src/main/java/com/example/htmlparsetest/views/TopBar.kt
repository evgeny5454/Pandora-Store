package com.example.htmlparsetest.views

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState){
    TopAppBar(
        title = { Text(text = "Pandora Store", fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = Color.DarkGray,
        contentColor = Color.White
    )
}
@Composable
fun TopBar(navController: NavController, title: String){
    TopAppBar(
        title = { Text(text = title, fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                //scope.launch {
                    navController.popBackStack()
                //}
            }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },
        backgroundColor = Color.DarkGray,
        contentColor = Color.White
    )
}

@Preview(showBackground = false)
@Composable
fun TopBarPreview(){
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    TopBar(scope = scope, scaffoldState = scaffoldState)
}