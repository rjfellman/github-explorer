package com.example.githubexplorer.data.repository

import com.example.githubexplorer.data.remote.GithubApi
import com.example.githubexplorer.data.remote.dto.RepoDto
import com.example.githubexplorer.domain.repository.RepoRepository

import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : RepoRepository {

    override suspend fun getReposByOrg(org: String): List<RepoDto> {
        return api.getReposByOrg(org)
    }
}