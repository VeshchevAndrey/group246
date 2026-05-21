// package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.application246.ui.theme.Application246Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application246Theme() {
                Scaffold() { paddingValues ->
                    ApplicationScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

// animateDpAsState() = выполняет анимацию значений, заданных через .dp (размеры, отступы и т.п.)
// tween() - функция управления анимацией

@Composable
fun ApplicationScreen(modifier: Modifier){
    val startOffset = 0
    val endOffset = 100
    val startSize = 150
    val endSize = 250

    val textState = remember { mutableStateOf("") }

    val boxOffset = remember { mutableStateOf(startOffset) }
    val offsetAnimation = animateDpAsState(
        targetValue = boxOffset.value.dp,
        animationSpec = tween(durationMillis = 1000)
    )

    val imageSize = remember { mutableStateOf(startSize) }
    val sizeAnimation = animateDpAsState(
        targetValue = imageSize.value.dp,
        animationSpec = tween(
            durationMillis = 2500,
            delayMillis = 500,
            easing = CubicBezierEasing(0f, 1f, 0.5f, 1f)
        ),
        finishedListener = {
            textState.value = "Анимация завершена!"
        }
    )

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .offset(offsetAnimation.value)
                .background(Color(0xFFFF5722))
                .size(startSize.dp)
                .clickable(onClick = {
                    boxOffset.value = if (boxOffset.value == startOffset) endOffset else startOffset
                })
        ) { }
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.dog),
            contentDescription = "Dog",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(sizeAnimation.value)
                .clickable(onClick = {
                    imageSize.value = if (imageSize.value == startSize) endSize else startSize
                })
        )
        Text(text = textState.value)
    }
}
