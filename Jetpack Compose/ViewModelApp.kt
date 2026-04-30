// package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application246.ui.theme.Application246Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application246Theme(dynamicColor = false) {
                Scaffold() { paddingValues ->
                    ApplicationScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

// Приложение с реализацией "всё в одном"
//@Composable
//fun ApplicationScreen(modifier: Modifier = Modifier){
//    val count = rememberSaveable { mutableStateOf(0) }
//
//    Column(modifier = modifier) {
//        Text(text = "Вы кликнули по кнопке ${count.value} раз(а)")
//        Button(onClick = { count.value++ }) { Text(text = "ЖМИ!") }
//    }
//}

// Приложение с реализацией ViewModel
class ClickerViewModel : ViewModel() {
    val count = mutableStateOf(0)

    fun increase() {
        count.value++
    }
}

@Composable
fun ApplicationScreen(modifier: Modifier = Modifier, viewModel: ClickerViewModel = viewModel()) {
    Column(modifier = modifier) {
        Text(text = "Вы кликнули по кнопке ${viewModel.count.value} раз(а)")
        Button(onClick = { viewModel.increase() }) { Text(text = "ЖМИ!") }
    }
}
