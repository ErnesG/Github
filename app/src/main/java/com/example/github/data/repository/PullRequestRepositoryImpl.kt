package com.example.github.data.repository

import com.example.github.data.api.GithubService
import com.example.github.data.model.PullRequestResponse
import com.example.github.domain.model.Resource
import com.example.github.domain.repository.PullRequestRepository
import javax.inject.Inject

class PullRequestRepositoryImpl @Inject constructor(private val api: GithubService): PullRequestRepository {
    override suspend fun getPullRequests(): Resource<List<PullRequestResponse>> {
        return try {
            val response = api.getPullRequests()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An error occurred")
        }
    }

}