package com.example.github.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.github.data.model.PullRequestResponse

@Composable
fun PullRequestListView(
    pullRequests: List<PullRequestResponse>,
    isLoading: Boolean,
    onRefresh: () -> Unit,
    onFilterSelected: (String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom // Push the button to the bottom
    ) {
        // filter buttons row
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilterButton(label = "All", onClick = { onFilterSelected(null) })
            FilterButton(label = "bug", onClick = { onFilterSelected("bug") })
            FilterButton(label = "enhancement", onClick = { onFilterSelected("enhancement") })
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Take up available space
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) { // Take up available space
                items(pullRequests) { pr ->
                    PullRequestItem(pr)
                }
            }
        }
        Button(
            onClick = onRefresh,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 8.dp)
        ) {
            Text("Refreshing")
        }
    }
}