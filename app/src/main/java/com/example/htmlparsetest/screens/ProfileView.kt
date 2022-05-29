package com.example.htmlparsetest.screens

import android.content.Context
import android.content.Intent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.navigation.NavController
import com.example.htmlparsetest.Screen
import com.example.htmlparsetest.activity
import com.example.htmlparsetest.views.TopBar

@Composable
fun ProfileView(navController: NavController) {

    //val activity =

    Scaffold(
        topBar = {  TopBar(navController = navController , title = "Profile View") }
    ) {
        TextButton(onClick = {
            //navController.navigate(Screen.FileManagerView.route)
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "*/*"
            startActivityForResult(activity,intent,12, null)
        } ) {
            Text(text = "Загрузить прайс")
        }
    }

}