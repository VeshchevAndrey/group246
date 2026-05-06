package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    CalculatorScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier, vm: CalculatorViewModel = viewModel()){
    val buttons = arrayOf(
        stringArrayResource(R.array.row_1),
        stringArrayResource(R.array.row_2),
        stringArrayResource(R.array.row_3),
        stringArrayResource(R.array.row_4),
        stringArrayResource(R.array.row_5)
    )

    Column(
        modifier = modifier.fillMaxSize().background(Color.Black)
    ) {
        Text(
            text = vm.calculatorState.value.display,
            modifier = Modifier.fillMaxWidth().weight(2f),
            color = Color.White,
            fontSize = 72.sp,
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Thin,
            maxLines = 1
        )

        val isOperator = arrayOf("/", "x", "-", "+", "=")

        buttons.forEach { row ->
            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                row.forEach { label ->
                    CalculatorButton(
                        label = label,
                        color = (if (label in isOperator) Color.Red else Color.White),
                        colorText = (if (label in isOperator) Color.White else Color.Black),
                        modifier = Modifier.weight(if (label == "0") 2f else 1f)
                    ) { vm.onButtonClick(label) }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    label: String,
    modifier: Modifier = Modifier,
    color: Color,
    colorText: Color,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        shape = RectangleShape,
        colors = (ButtonDefaults.buttonColors(containerColor = color, contentColor = colorText)),
        modifier = modifier.padding(1.dp).fillMaxHeight()
    ) {
        Text(
            text = label,
            fontSize = 36.sp
        )
    }
}