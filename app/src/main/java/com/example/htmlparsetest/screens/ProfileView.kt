package com.example.htmlparsetest.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.htmlparsetest.Screen
import com.example.htmlparsetest.views.TopBar

@Composable
fun ProfileView(navController: NavController) {
    Scaffold(
        topBar = {  TopBar(navController = navController , title = "Profile View") }
    ) {
        TextButton(onClick = { navController.navigate(Screen.FileManagerView.route) } ) {
            Text(text = "Загрузить прайс")
        }
    }
}