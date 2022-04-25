package com.example.htmlparsetest.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.htmlparsetest.Product
import com.example.htmlparsetest.R
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun MyDialog(
    onDismiss: () -> Unit,
    onNegativeClick: (product: Product) -> Unit,
    onPositiveClick: (count: Int, product: Product) -> Unit,
    product: Product,
    count: Int
) {
    val myCount = remember {
        mutableStateOf(count)
    }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        val color = Color(0xFF4DB64C)
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                    //.height(300.dp)
                    //.background(color = color)
                ) {
                    Row(
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GlideImage(
                            imageModel = product.imageUrl,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(horizontalAlignment = Alignment.Start) {
                            Subtitle5(text = product.title)
                            Caption(text = "$count шт.", color = Color.Gray)
                        }
                    }


                }
                Column(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.medium
                    ) {
                        Text(
                            text = "Введите необходимое количество для заказа"
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_arrow_up),
                                contentDescription = "Count Up",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clickable {
                                        myCount.value++
                                    }
                                    .weight(0.2f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = myCount.value.toString(),
                                style = TextStyle(fontSize = 50.sp),
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_arrow_down),
                                contentDescription = "Count Down",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clickable {
                                        if (myCount.value > 1) {
                                            myCount.value--
                                        }
                                    }
                                    .weight(0.2f)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Удалить товар",
                        style = TextStyle(fontSize = 20.sp),
                        color = color,
                        modifier = Modifier
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = rememberRipple(color = Color.DarkGray),
                                onClick = {
                                    onNegativeClick.invoke(product) }
                            )
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.width(30.dp))

                    Text(
                        text = "Да",
                        style = TextStyle(fontSize = 20.sp),
                        color = color,
                        modifier = Modifier
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = rememberRipple(color = Color.DarkGray),
                                onClick = {
                                    onPositiveClick.invoke(myCount.value, product)
                                }
                            )
                            .padding(8.dp)
                    )
                }

            }
        }
    }
}