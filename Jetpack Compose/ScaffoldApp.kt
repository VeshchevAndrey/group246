package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Точка запуска приложения (аналог main() в Kotlin)
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // функция размещения и отрисовки элементов интерфейса
        setContent {
            // Функция установки общей структуры окна приложения
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Опросник") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFF6750A3),
                            titleContentColor = Color(0xFFFFFFFF)
                        )
                    )
                }
            ) { paddingValues -> // передача значений отступов в функции контента
                Application2(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
fun Application2(modifier: Modifier){
    val textInput = remember { mutableStateOf(value = "") }
    val checkChange = remember { mutableStateOf(value = false) }
    var checkChange2 by remember { mutableStateOf(value = false) }
    var radioChange by remember { mutableStateOf(false) }

    val optionRadio = arrayOf("Да", "Нет", "Не уверен")
    val (radioChange2, onSelected) = remember { mutableStateOf(optionRadio[0]) }
    val textButton = rememberSaveable { mutableStateOf(value = "Нажми на меня") }

    Column(modifier = modifier.padding(10.dp, 5.dp)) {
        OutlinedTextField(
            value = textInput.value,
            onValueChange = {textInput.value = it},
            label = {Text("Введите ваше имя:")}
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checkChange.value,
                onCheckedChange = {checkChange.value = it}
            )
            Text(text = "Ты человек?")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checkChange2,
                onCheckedChange = {checkChange2 = it}
            )
            Text(text = "Ты используешь ИИ?")
        }
        Text(text = "Выбери один из двух")
        Row() {
            RadioButton(
                selected = radioChange,
                onClick = {radioChange = !radioChange}
            )
            RadioButton(
                selected = !radioChange,
                onClick = {radioChange = !radioChange}
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            for (i in optionRadio){
                RadioButton(
                    selected = (radioChange2 == i),
                    onClick = {onSelected(i)}
                )
                Text(text = i)
            }
        }
        Button(
            onClick = {textButton.value = "Ура, нажал!"}
        ) { Text(text = textButton.value) }
    }
}

@Preview(showBackground = true) // аннотация, позволяющая использовать режим Split для предпросмотра
@Composable
fun PreviewForMyFunctions(){
//    Application2()
}
