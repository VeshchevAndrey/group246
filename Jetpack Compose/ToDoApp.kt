// package com.example.application246

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun ToDoScreen(){
    val tasks = remember { mutableStateListOf<Task>() }
    val newTask = remember { mutableStateOf("") }

    Column() {
        Text(text = "Выполнено ${tasks.count { it.isDone }} задач из ${tasks.size}")
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newTask.value,
                onValueChange = { newTask.value = it },
                placeholder = { Text("New task") },
                modifier = Modifier.weight(1f),
            )
            Button(
                onClick = {
                    if ((newTask.value.isNotBlank())){
                        tasks.add(Task(title = newTask.value))
                        newTask.value = ""
                    }
                }
            ) { Text(text = "Add") }
        }
        LazyColumn() {
            items(items = tasks) { item ->
                SingleTask(
                    task = item,
                    onStatusChange = {
                        val index = tasks.indexOf(item)
                        if (index >= 0){
                            tasks[index] = item.copy(isDone = !item.isDone)
                        }
                    },
                    onDelete = { tasks.remove(item) }
                )
            }
        }
    }
}

@Composable
fun SingleTask(task: Task, onStatusChange: () -> Unit, onDelete: () -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = Modifier,
            checked = task.isDone,
            onCheckedChange = { onStatusChange() }
        )
        Text(
            modifier = Modifier.weight(1f),
            text = task.title
        )
        IconButton(
            modifier = Modifier, onClick = { onDelete() }
        ) {
            Icon(imageVector = Icons.Rounded.Delete, "Delete")
        }
    }
}

// Объявление Data-класса
data class Task(
    val title: String,
    val isDone: Boolean = false
)

@Preview(showBackground = true)
@Composable
fun PreviewFunction(){
    ToDoScreen()
}
