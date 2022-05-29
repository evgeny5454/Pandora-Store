package com.example.htmlparsetest.screens

import android.os.Environment
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.htmlparsetest.Screen
import com.example.htmlparsetest.views.FilesList
import com.example.htmlparsetest.views.TopBar
import java.io.File

@Composable
fun FileManagerView(navController: NavController, files: File, onClick: (File) -> Unit) {
    Scaffold(topBar = { TopBar(navController = navController, title = "File Manager") }
    ) {
        //val files = Environment.getExternalStorageDirectory()


        //var files = Environment.getExternalStorageState(path)
        //files = Environment.getExternalStorageState(files.absoluteFile)
        FilesList(files = files.listFiles(), onClick = {
            onClick.invoke(it)
            navController.navigate(Screen.FileManagerView.route)
        })

        /*file?.forEach { file ->
            Log.d("ListFiles", file.name)
        }*/

    }
}