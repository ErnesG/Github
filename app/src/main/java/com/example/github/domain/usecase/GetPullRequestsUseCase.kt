package com.example.github.domain.usecase

import com.example.github.data.model.PullRequestResponse
import com.example.github.domain.model.Resource
import com.example.github.domain.repository.PullRequestRepository
import javax.inject.Inject

class GetPullRequestsUseCase @Inject constructor(private val repository: PullRequestRepository) {
       suspend operator fun invoke(): Resource<List<PullRequestResponse>> {
           return repository.getPullRequests()
       }
}