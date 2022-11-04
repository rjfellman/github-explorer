package com.example.githubexplorer.presentation.repo_list

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubexplorer.common.Constants
import com.example.githubexplorer.common.Resource
import com.example.githubexplorer.domain.use_case.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val getReposUseCase: GetReposUseCase
) : ViewModel() {

    private val _state = mutableStateOf(RepoListState())
    val state: State<RepoListState> = _state

    var search by mutableStateOf(Constants.INITIAL_ORG)

    init {
        getRepos(Constants.INITIAL_ORG)
    }

    fun refreshRepos(org: String) {
        getRepos(org)
    }

    private fun getRepos(org: String) {
        getReposUseCase(org).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = RepoListState(repos = result.data?.sortedByDescending({ it.stars })?.take(3) ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = RepoListState(
                        error = result.message ?: "An unexpected error occurred."
                    )
                }
                is Resource.Loading -> {
                    _state.value = RepoListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}