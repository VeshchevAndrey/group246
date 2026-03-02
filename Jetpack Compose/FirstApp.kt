package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// Точка запуска приложения (аналог main() в Kotlin)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // функция размещения и отрисовки элементов интерфейса
        setContent {
            val userName = "Андрей"
            val timeNow: Byte = 2
            GreetingText(userName, timeNow)
        }
    }
}

@Composable // аннотация, указывающая что функция содержит элемент интерфейса
fun GreetingText(name: String, hour: Byte){
    var text = ""
    if (hour in 12..17)
        text = "Добрый день"
    else if (hour in 18..21)
        text = "Добрый вечер"
    else
        text = "Добро пожаловать"
    Text("$text, $name") // Composable-функция, представлящая текст в приложении
}

@Preview(showBackground = true) // аннотация, позволяющая использовать режим Split для предпросмотра
@Composable
fun PreviewForMyFunctions(){
    GreetingText("admin", 20)
}
