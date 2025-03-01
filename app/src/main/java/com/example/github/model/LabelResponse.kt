package com.example.github.model

data class LabelResponse(
    val id: Long,
    val name: String,
    val color: String,
    val description: String?
)
