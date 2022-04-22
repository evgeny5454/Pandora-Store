package com.example.htmlparsetest.views

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Subtitle5(text: String, modifier: Modifier = Modifier) = Text(
    modifier = modifier,
    text = text,
    style = TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Normal)
)

@Composable
fun Caption(text: String, modifier: Modifier = Modifier, color: Color) = Text(
    modifier = modifier,
    text = text,
    style = TextStyle(color = color, fontSize = 14.sp, fontWeight = FontWeight.Normal)
)

@Composable
fun SubtitleMedium4(text: String, modifier: Modifier = Modifier) = Text(
    modifier = modifier,
    text = text,
    style = TextStyle(color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Medium)
)