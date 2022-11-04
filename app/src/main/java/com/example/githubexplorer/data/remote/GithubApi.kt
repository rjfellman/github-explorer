package com.example.githubexplorer.data.remote

import com.example.githubexplorer.common.Constants
import com.example.githubexplorer.data.remote.dto.RepoDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubApi {
        @Headers("Authorization: Bearer " + Constants.AUTH_TOKEN)
        @GET("/orgs/{org}/repos")
        suspend fun getReposByOrg(@Path(Constants.PARAM_ORG_ID) org: String): List<RepoDto>
}
