package com.example.htmlparsetest.views

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.htmlparsetest.R
import java.io.File
import java.nio.file.Path

@Composable
fun FileItem(file: File, onClick: (File) ->Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick.invoke(file)
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_folder_24),
            contentDescription = R.drawable.ic_baseline_folder_24.toString(),
            modifier = Modifier.size(100.dp)
        )
        Text(text = file.name, modifier = Modifier.align(Alignment.CenterVertically))
    }
}