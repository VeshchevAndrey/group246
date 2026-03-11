// package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Точка запуска приложения (аналог main() в Kotlin)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // функция размещения и отрисовки элементов интерфейса
        setContent {

        }
    }
}

@Composable
fun RowAndColumnFunction(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColumnFunction("Walter", "White", 51)
        RowFunction()
    }
}

@Composable
fun ColumnFunction(name: String, surname: String, age: Byte){
    // Размещение объектов вертикально относительно друг друга (в колонку)
    Column(
        modifier = Modifier.size(250.dp, 150.dp),
        verticalArrangement = Arrangement.SpaceEvenly, // вертикальное выравнивание внутри контейнера
        horizontalAlignment = Alignment.CenterHorizontally // горизонтальное выравнивание внутри контейнера
    ) {
        Text(text = "Имя: $name")
        Text(text = "Фамилия: $surname")
        Text(text = "Возраст: $age")
    }
}

@Composable
fun RowFunction(){
    // Размещение объектов горизонтально относительно друг друга (в строку)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround, // горизонтальное выравнивание внутри контейнера
        verticalAlignment = Alignment.CenterVertically // вертикальное выравнивание внутри контейнера
    ) {
        Text(
            text = "Готов?",
//            modifier = Modifier.weight(2f)
        )
        Button(
            onClick = {},
//            modifier = Modifier.weight(4f)
        ) { Text("Готов!") }
    }
}

@Preview(showBackground = true) // аннотация, позволяющая использовать режим Split для предпросмотра
@Composable
fun PreviewForMyFunctions(){
    RowAndColumnFunction()
}
