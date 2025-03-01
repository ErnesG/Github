package com.example.github.presentation.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.github.data.model.PullRequestResponse

@Composable
fun PullRequestItem(pr: PullRequestResponse) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = pr.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            // display all labels as chips
            Row {
                pr.labels.forEach { label->
                    Chip(label = label.name)
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Opened: ${pr.createdAt}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "View pull request",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse((pr.htmlUrl)))
                    context.startActivity(intent)
                }
            )
        }

    }
}