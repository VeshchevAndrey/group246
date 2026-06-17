package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

// Точка запуска приложения (аналог main() в Kotlin)
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // функция размещения и отрисовки элементов интерфейса
        setContent {
            ApplicationScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationScreen(viewModel: ProfileViewModel = viewModel()){
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Профиль") },
                actions = {
                    IconButton(onClick = {
                        viewModel.shareProfile(
                            context = context,
                            profile = UserProfile(
                                name = viewModel.username.value,
                                phone = viewModel.phone.value,
                                email = viewModel.email.value
                            )
                        )
                    }) {
                        Icon(Icons.Rounded.Share, "Share")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TextField(
                value = viewModel.username.value,
                onValueChange = { viewModel.updateName(it) },
                placeholder = { Text("Имя") }
            )
            TextField(
                value = viewModel.phone.value,
                onValueChange = { viewModel.updatePhone(it) },
                placeholder = { Text("Телефон") }
            )
            TextField(
                value = viewModel.email.value,
                onValueChange = { viewModel.updateEmail(it) },
                placeholder = { Text("Email") }
            )
        }
    }
}