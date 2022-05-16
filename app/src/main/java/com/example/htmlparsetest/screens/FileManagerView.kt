package com.example.htmlparsetest.screens

import android.os.Environment
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.htmlparsetest.views.FilesList
import com.example.htmlparsetest.views.TopBar

@Composable
fun FileManagerView(navController: NavController){
    Scaffold(topBar = { TopBar(navController = navController , title = "File Manager") }
    ) {
        val path = Environment.getExternalStorageDirectory()
        var files = Environment.getExternalStorageState(path)
        //files = Environment.getExternalStorageState(files.absoluteFile)
        FilesList(files = files)

        /*file?.forEach { file ->
            Log.d("ListFiles", file.name)
        }*/

    }
}