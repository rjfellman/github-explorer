package com.example.githubexplorer.domain.repository

import com.example.githubexplorer.data.remote.dto.RepoDto

interface RepoRepository {
    suspend fun getReposByOrg(org: String): List<RepoDto>
}