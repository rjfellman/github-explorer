package com.example.githubexplorer.presentation.repo_list

import com.example.githubexplorer.data.Repo

data class RepoListState(
    val isLoading: Boolean = false,
    val repos: List<Repo> = emptyList(),
    val error: String = ""
)