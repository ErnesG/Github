package com.example.github.model

import com.google.gson.annotations.SerializedName

data class PullRequestResponse(
    val id: Long,
    val title: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("created_at")
    val createdAt: String,
    val labels: List<LabelResponse>
)
