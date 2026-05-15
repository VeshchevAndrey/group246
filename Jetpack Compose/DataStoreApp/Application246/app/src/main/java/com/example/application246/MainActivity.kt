package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application246.ui.theme.Application246Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userDataRepository = UserDataRepository(this)
        val viewModel = UserDataViewModel(userDataRepository)

        setContent {
            Application246Theme(dynamicColor = false) {
                Scaffold() { paddingValues ->
                    ApplicationScreen(modifier = Modifier.padding(paddingValues), vm = viewModel)
                }
            }
        }
    }
}

@Composable
fun ApplicationScreen(modifier: Modifier, vm: UserDataViewModel){
    val userName = vm.currentUser.collectAsState()

    Column(modifier = modifier) {
        TextField(value = vm.inputText.value, onValueChange = {vm.updateTextInput(it)})
        Button(onClick = { vm.updateName() }) { Text(text = "Подтвердить") }
        Text(text = "Текущий пользователь: ${userName.value}")
    }
}