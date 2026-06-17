package com.example.application246

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    val username = mutableStateOf("")
    val phone = mutableStateOf("")
    val email = mutableStateOf("")

    fun updateName(newValue: String){
        username.value = newValue
    }
    fun updatePhone(newValue: String){
        phone.value = newValue
    }
    fun updateEmail(newValue: String){
        email.value = newValue
    }

    fun shareProfile(context: Context, profile: UserProfile){
        val text = """
            Данные профиля
            Имя: ${profile.name}
            Телефон: ${profile.phone}
            Email: ${profile.email}
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Поделиться профилем")
        context.startActivity(shareIntent)
    }
}