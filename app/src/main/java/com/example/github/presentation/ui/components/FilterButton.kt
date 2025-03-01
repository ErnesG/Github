package com.example.github.presentation.ui.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun FilterButton(label: String, onClick: ()-> Unit) {
    Button(onClick = onClick) {
        Text(text = label)
    }
}