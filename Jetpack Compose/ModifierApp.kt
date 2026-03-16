// package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Точка запуска приложения (аналог main() в Kotlin)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // функция размещения и отрисовки элементов интерфейса
        setContent {
            Application2()
        }
    }
}

@Composable
fun Application2(){
    val modifierForText: Modifier = Modifier
        .padding(5.dp, 5.dp, 5.dp, 0.dp)
    
    Column(
        modifier = Modifier
            .background(Color(0xFFFF9100))
            .fillMaxWidth()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "-Привет сосед!",
            modifier = modifierForText,
            color = Color(0xFFF3E5F5),
            fontSize = 25.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Text(
            text = "Ответить \"Привет\"",
            modifier = Modifier
                .padding(5.dp)
                .background(Color(0xFFFF3D00), RoundedCornerShape(5.dp))
                .padding(5f.dp)
                .clickable(onClick = {}),
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true) // аннотация, позволяющая использовать режим Split для предпросмотра
@Composable
fun PreviewForMyFunctions(){
    Application2()
}
