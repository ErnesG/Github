package com.example.github.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Chip(label: String) {
    Surface (shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colors.primary.copy(alpha = .2f)) {
        Text(
            text = label,
            modifier =  Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.caption
        )
    }
}