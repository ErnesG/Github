package com.example.github.data.api

import com.example.github.data.model.PullRequestResponse
import retrofit2.http.GET

interface GithubService {
    @GET("repos/divvydose/ui-coding-challenge/pulls")
    suspend fun getPullRequests(): List<PullRequestResponse>
}