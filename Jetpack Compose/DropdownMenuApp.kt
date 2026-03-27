// package com.example.application246

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.application246.ui.theme.Application246Theme

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
                                    imageVector = ImageVector.vectorResource(R.drawable.dog_icon),
                                    contentDescription = "Dog Icon"
                                )
                            }
                            MyDropdownMenu()
                        },
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFFFF5722),
                            actionIconContentColor = Color(0xFFFFFFFF),
                            navigationIconContentColor = Color(0xFFFFFFFF),
                            titleContentColor = Color(0xFFFFFFFF)
                        )
                    )
                }
            ) {
                ImageFunction(modifier = Modifier.padding(it))
            }
        }
    }
}

@Composable
fun MyDropdownMenu(){
    val dropdownState = remember { mutableStateOf(false) }
    val dialogState = remember { mutableStateOf(false) }

    IconButton(onClick = { dropdownState.value = true }) {
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "More"
        )
    }

    // Функция выпадающего меню
    DropdownMenu(
        expanded = dropdownState.value,
        onDismissRequest = { dropdownState.value = false }
    ) {
        // Отдельный пункт выпадающего меню
        DropdownMenuItem(
            text = { Text("Refresh") },
            onClick = {},
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "Refresh")
            }
        )
        HorizontalDivider(
            thickness = 5.dp
        )
        DropdownMenuItem(
            text = { Text("About") },
            onClick = { dialogState.value = true},
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Info, contentDescription = "About")
            }
        )
    }

    if (dialogState.value){
        // Функция диалогового окна
        AlertDialog(
            onDismissRequest = { dialogState.value = false },
            title = { Text(text = "About the Developer") },
            text = { Text(text = "Designed by me") },
            confirmButton = {
                Button(onClick = { dialogState.value = false }) { Text(text = "OK") }
            }
        )
    }
}

@Composable
fun ImageFunction(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
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
