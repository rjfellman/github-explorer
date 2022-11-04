package com.example.githubexplorer.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.githubexplorer.data.Repo

@Composable
fun RepoListItem(
    repo: Repo,
    onItemClick: (Repo) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(repo) }
            .padding(20.dp)
            .testTag("repoEntry"),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${repo.name}",
            fontStyle = FontStyle.Normal,
            style = MaterialTheme.typography.h6,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "Stars: ${repo.stars}",
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(End)
        )
        Text(
            text = "${repo.description}",
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(CenterHorizontally)
        )
    }
}