package com.example.github.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.github.presentation.viewmodel.PullRequestViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.livedata.observeAsState
import com.example.github.domain.model.Resource
import com.example.github.presentation.ui.components.PullRequestListView


@AndroidEntryPoint
class PullRequestListFragment: Fragment() {
    private val viewModel: PullRequestViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val prList = viewModel.filteredPullRequests.observeAsState(emptyList()).value
                val loading = viewModel.pullRequests.observeAsState().value is Resource.Loading

                PullRequestListView(pullRequests = prList,
                    isLoading = loading,
                    onRefresh = {
                        viewModel.fetchPullRequests()
                    },
                    onFilterSelected = {
                        label -> viewModel.setFilter(label)
                    }
                )
            }
        }
    }
}