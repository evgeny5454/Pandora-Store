package com.example.htmlparsetest.views

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import java.io.File

@Composable
fun FilesList(files: Array<File>?, onClick : (File) -> Unit){
    LazyColumn{
        files?.forEach { file ->  
            item { FileItem(file = file, onClick = { onClick.invoke(it)}) }
        }
    }
}