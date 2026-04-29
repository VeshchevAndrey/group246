// package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold() { paddingValues ->
                ApplicationApp(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
fun ApplicationApp(modifier: Modifier = Modifier){
    val clickCount = remember() { mutableStateOf(0) }
    val changeableString = remember { mutableStateOf("Нажми на строку!") }
    val changeableColor = remember { mutableStateOf(Color(0xFFFFFFFF)) }
    val xOffset = remember { mutableStateOf(0f) }
    val yOffset = remember { mutableStateOf(0f) }

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { clickCount.value++ })
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.dog),
                contentDescription = "Dog image",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = "Ты нажал на данную строку ${clickCount.value} раз(а)!")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(changeableColor.value)
                .pointerInput(Unit) {
                    detectTapGestures (
                        onTap = {
                            changeableString.value = "Вы нажали по объекту!"
                            changeableColor.value = Color(0xFFFFFFFF)
                                },
                        onLongPress = { changeableString.value = "Вы уже долго жмёте по объекту!" },
                        onDoubleTap = { changeableString.value = "Вы нажали по объекту 2 раза подряд!" },
                        onPress = {
                            changeableString.value = "Вы продолжаете жать на объект!"
                            changeableColor.value = Color(0xFFD2D1D1)
                        }
                    )
                }
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.dog2),
                contentDescription = "Dog image",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = changeableString.value)
        }
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.car_placeholder),
            contentDescription = "Сar Image",
            modifier = Modifier
                .size(100.dp)
                .offset(xOffset.value.dp, yOffset.value.dp)
                .pointerInput(Unit){
                    detectDragGestures { _, distance ->
                        xOffset.value += distance.x
                        yOffset.value += distance.y
                    }
                }
            ,
            contentScale = ContentScale.Crop
        )
    }
}
