// package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import com.example.application246.ui.theme.Application246Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Application246Theme(dynamicColor = false) {
                ApplicationApp()
            }
        }
    }
}

@Composable
fun ApplicationApp(){
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("Это сделала всплывающая кнопка!")
                }
            }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Button(onClick = {
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "Ого, всплывающее окно!",
                        actionLabel = "Подтвердить",
                        duration = SnackbarDuration.Indefinite,
                        withDismissAction = true
                        )
                    when (result) {
                        SnackbarResult.ActionPerformed -> {
                            snackbarHostState.showSnackbar("Действие выполнено!")
                        }
                        SnackbarResult.Dismissed -> {
                            snackbarHostState.showSnackbar("Действие отменено!")
                        }
                    }
                }
            }) { Text(text = "Нажми — увидишь что-то!") }
            Text(text = "А я не всплывающее окно!")
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.dog),
                contentDescription = "Dog",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
