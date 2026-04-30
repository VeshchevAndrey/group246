// package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.application246.ui.theme.Application246Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application246Theme(dynamicColor = false) {
                Scaffold() { paddingValues ->
                    CurrencyScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun CurrencyScreen(modifier: Modifier = Modifier){
    val rublesValue = remember() { mutableStateOf("") }
    val dollarExchangeRateState = remember { mutableStateOf("") }
    val dollarValue = remember { derivedStateOf {
        ((rublesValue.value.toDoubleOrNull() ?: 0.0)
                / (dollarExchangeRateState.value.toDoubleOrNull() ?: 0.0))
    } }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = rublesValue.value,
            onValueChange = { rublesValue.value = it },
            label = { Text(text = "Введите значение в рублях:") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = dollarExchangeRateState.value,
            onValueChange = { dollarExchangeRateState.value = it },
            label = { Text(text = "Введите текущий доллара:") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = if ((rublesValue.value != "") and (dollarExchangeRateState.value != "")) {
            "Значение в долларах: ${"%.2f".format(dollarValue.value)}"
        } else "Введите значения выше",
            style = MaterialTheme.typography.labelSmall
        )
    }
}
