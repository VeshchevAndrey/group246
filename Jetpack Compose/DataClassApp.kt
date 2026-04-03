// package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(R.string.app_name)) },
                        actions = {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Rounded.MoreVert,
                                    contentDescription = "More Icon"
                                )
                            }
                        }
                    )
                }
            ) {
                CompanionScreen(modifier = Modifier.padding(it))
            }
        }
    }
}

val singleCompanion = Companion(
    name = "Cloud Strife",
    lastMessage = "Whatever",
    image = R.drawable.i1,
    isRead = false
)

val companions = arrayOf(
    Companion("Tifa Lockhart", "Hello", R.drawable.i2, true),
    Companion("Aerith Gainsborough", "Hi", R.drawable.i3, true),
    Companion("Barret Wallace", "No, thanks", R.drawable.i4, false)
)

@Composable
fun CompanionScreen(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        CompanionFunction(singleCompanion)
        companions.forEach {
            CompanionFunction(it)
        }
    }
}

@Composable
fun CompanionFunction(companion: Companion){
    Row(
        modifier = Modifier.padding(vertical = 2.5f.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(companion.image),
            contentDescription = "${companion.name}'s avatar",
            modifier = Modifier.padding(5.dp).clip(CircleShape).size(75.dp)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = companion.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(text = companion.lastMessage, fontSize = 20.sp)
        }
        Icon(
            imageVector = ImageVector.vectorResource(
                if (!companion.isRead) R.drawable.rounded_check_24 else R.drawable.double_check
            ),
            contentDescription = "Is Read Icon",
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompanionScreen()
}

data class Companion(
    val name: String,
    val lastMessage: String,
    val image: Int,
    val isRead: Boolean
)
