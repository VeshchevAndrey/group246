// package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
// animateColorAsState() - анимирует значение цвета типа Color
// animateIntAsState() - выполняет анимацию значений, заданных типом данных Int
// tween() - функция управления анимацией
// keyframes{} - функция управления ключевыми кадрами анимации
// AnimatedVisibility - контейнер, управляющий видимостью объекта через его появление и исчезновение

@Composable
fun ApplicationScreen(modifier: Modifier){
    val startColor = Color(0xFF00BCD4)
    val endColor = Color(0xFFFF5722)

    val colorState = remember { mutableStateOf(startColor) }
    val colorAnimation = animateColorAsState(
        targetValue = colorState.value,
        animationSpec = tween(durationMillis = 1000)
    )

    val colorStateExtended = remember { mutableStateOf(startColor) }
    val colorAnimationExtended = animateColorAsState(
        targetValue = colorStateExtended.value,
        animationSpec = keyframes {
            durationMillis = 3000
            Color(0xFF3F51B5) at 500
            Color(0xFF009688) at 1500
            Color(0xFFFFEB3B) at 2500
        }
    )

    val clicked = remember { mutableStateOf(false) }
    val sizeAnimation = animateIntAsState(
        targetValue = if (clicked.value) 14 else 16
    )

    val imageVisible = remember { mutableStateOf(true) }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .clickable(onClick = {
                    colorState.value = if (colorState.value == startColor) endColor else startColor
                })
                .fillMaxWidth()
                .background(color = colorAnimation.value)
                .padding(10.dp)
        ) {
            Text(text = "Нажми на меня")
        }
        Spacer(modifier = Modifier.size(5.dp))
        Row(
            modifier = Modifier
                .clickable(onClick = {
                    colorStateExtended.value = if (colorStateExtended.value == startColor)
                        endColor else startColor
                })
                .fillMaxWidth()
                .background(colorAnimationExtended.value)
                .padding(10.dp)
        ){
            Text(text = "Нажми и на меня тоже!")
        }
        Spacer(modifier = Modifier.size(5.dp))
        Row(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { clicked.value = !clicked.value },
                        onTap = { clicked.value = !clicked.value }
                    )
                }
                .fillMaxWidth()
                .background(endColor)
                .padding(10.dp)
        ){
            Text(
                text = "И про меня не забудь!",
                fontSize = sizeAnimation.value.sp)
        }
        Spacer(modifier = Modifier.size(5.dp))
        AnimatedVisibility(
            visible = imageVisible.value,
            exit = fadeOut() + slideOut(targetOffset = { IntOffset(180, 50)}),
            enter = fadeIn() + slideIn(initialOffset = {IntOffset(-180, -50)})
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.dog),
                contentDescription = "Dog",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.size(5.dp))
        Button(onClick = { imageVisible.value = !imageVisible.value }) {
            Text(text = "Управляй видимостью Dog'а")
        }
    }
}
