package com.example.github.domain.repository

import com.example.github.data.model.PullRequestResponse
import com.example.github.domain.model.Resource

interface PullRequestRepository {
    suspend fun getPullRequests(): Resource<List<PullRequestResponse>>
}