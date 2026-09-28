package com.example.experiment2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Task(
    val id: Int,
    val title: String,
    val initialCompleted: Boolean = false
) {
    var isCompleted by mutableStateOf(initialCompleted)
}