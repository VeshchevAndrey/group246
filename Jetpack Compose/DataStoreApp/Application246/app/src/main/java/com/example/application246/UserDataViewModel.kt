package com.example.application246

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserDataViewModel(private val repository: UserDataRepository) : ViewModel() {
    val inputText = mutableStateOf("")

    fun updateTextInput(text: String){
        inputText.value = text
    }

    fun updateName(){
        viewModelScope.launch {
            repository.saveUserName(name = inputText.value)
        }
        inputText.value = ""
    }

    val currentUser = repository.loadUserName.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1000),
        initialValue = "Loading..."
    )
}