package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.application246.ui.theme.Application246Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application246Theme {
                ImageFunction()
            }
        }
    }
}

@Composable
fun ImageFunction(){
    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.padding(10.dp).size(100.dp).clip(CircleShape),
                bitmap = ImageBitmap.imageResource(R.drawable.dog),
                contentDescription = "Dog",
                contentScale = ContentScale.Crop
            )
            Text(text = "A Dog")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.padding(10.dp).size(100.dp).clip(CircleShape),
                bitmap = ImageBitmap.imageResource(R.drawable.dog2),
                contentDescription = "Another Dog",
                contentScale = ContentScale.Crop
            )
            Text(text = "Another Dog")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageFunction()
}
