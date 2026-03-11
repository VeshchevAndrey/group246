// package com.example.application246 - здесь название Вашего приложения 

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

// Точка запуска приложения (аналог main() в Kotlin)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // функция размещения и отрисовки элементов интерфейса
        setContent {
            InputFunction()
        }
    }
}

@Composable // аннотация
fun InputFunction(){
    val inputMessage = remember { mutableStateOf("") }
    val hiddenMessage = remember { mutableStateOf("") }

    Column() {
        Text("My First App", fontSize = 20.sp)
        TextField(value = inputMessage.value, onValueChange = {inputMessage.value = it})
        Button(onClick = {
            if (inputMessage.value != ""){
                hiddenMessage.value = inputMessage.value
                inputMessage.value = ""
            }
        }) { Text("Click on me!") }
        Text(text = hiddenMessage.value)
    }
}

@Preview(showBackground = true) // аннотация, позволяющая использовать режим Split для предпросмотра
@Composable
fun PreviewForMyFunctions(){
    InputFunction()
}
