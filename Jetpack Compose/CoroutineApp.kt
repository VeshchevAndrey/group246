// package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.application246.ui.theme.Application246Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application246Theme(dynamicColor = false) {
                Scaffold() { paddingValues ->
                    ApplicationApp(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun ApplicationApp(modifier: Modifier){
    // Объявление области корутин - места, из которого корутины запускаются
    val coroutineScore = rememberCoroutineScope()
    val clickerCount = remember { mutableStateOf(0) }
    val coroutineCount = remember { mutableStateOf(5) }
    val enabled = remember { mutableStateOf(true) }
    val progress = remember { mutableStateOf(0f) }

    Column(modifier = Modifier) {
        Button(onClick = {
            coroutineScore.launch {
                imitateWork()
            }
        }) { Text(text = "Нажми на меня!") }
        Button(onClick = { clickerCount.value++ }) { Text("Кликай по мне!") }
        Text(text = "Вы кликнули ${clickerCount.value} раз(а)")
        Button(onClick = {
            coroutineScore.launch {
                enabled.value = false
                for (i in 5 downTo 1){
                    coroutineCount.value = i
                    progress.value += 0.2f
                    delay(1000)
                }
                coroutineCount.value = 5
                enabled.value = true
                progress.value = 0f
            }
        }, enabled = enabled.value) { Text(text = "Запускай корутину!") }
        Text(text = "Осталось до завершения работы корутины: ${coroutineCount.value}")
        LinearProgressIndicator(progress = { progress.value })
    }
}

// Создание корутины - прерываемой функции
suspend fun imitateWork(){
    println("Корутина начала работу")
    delay(5000)
    println("Корутина закончила работу")
}
