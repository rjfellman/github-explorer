package com.example.githubexplorer.presentation.repo_list

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubexplorer.presentation.components.RepoListItem

@Composable
fun RepoListView(
    navController: NavController,
    viewModel: RepoListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .testTag("searchContainer")) {
            TextField(
                value = viewModel.search,
                onValueChange = {
                    viewModel.search = it
                    viewModel.refreshRepos(it)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.3f)
                )
            )

            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)) {
                items(state.repos) { repo ->
                    RepoListItem(
                        repo = repo,
                        onItemClick = {
                            val builder = CustomTabsIntent.Builder()
                            builder.setShowTitle(true)
                            val customBuilder = builder.build()

                            customBuilder.launchUrl(navController.context, Uri.parse(repo.url))
                        }
                    )
                    Divider(color = Color.LightGray)
                }
            }
        }

        if(state.error.isNotBlank() && viewModel.search.isNotBlank()) {
            Text(
                text = "Unable to find results for that org.",
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}