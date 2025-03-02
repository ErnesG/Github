package com.example.github.presentation.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.model.PullRequestResponse
import com.example.github.domain.model.Resource
import com.example.github.domain.usecase.GetPullRequestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(private val getPullRequestsUseCase: GetPullRequestsUseCase): ViewModel() {

    private val _pullRequests = MutableLiveData<Resource<List<PullRequestResponse>>> ()
    val pullRequests: LiveData<Resource<List<PullRequestResponse>>> = _pullRequests

    // holds current filter  ( null means no filter)
    private val _filterLabel = MutableLiveData<String?>()
    val filterLabel: LiveData<String?> = _filterLabel

    fun fetchPullRequests() {
        viewModelScope.launch {
            _pullRequests.value = Resource.Loading
            val result = getPullRequestsUseCase()
            _pullRequests.value = result
        }
    }

    fun setFilter(label: String?) {
        _filterLabel.value = label
    }
    init {
        fetchPullRequests()
    }

    // filtered list combining pull requests and filterLabel
    val filteredPullRequests: LiveData<List<PullRequestResponse>> =
        MediatorLiveData<List<PullRequestResponse>>().apply {
            fun update() {
                val resource = _pullRequests.value
                val filter = _filterLabel.value
                val list = if(resource is Resource.Success) {

                    if(filter.isNullOrEmpty()) {
                        resource.data
                    } else {
                        resource.data.filter { pr ->
                            pr.labels.any { it.name.equals(filter, ignoreCase = true) }
                        }
                    }
                } else {
                    emptyList()
                }
                value = list
            }
            addSource(_pullRequests) { update() }
            addSource(_filterLabel) { update() }
        }
}



















