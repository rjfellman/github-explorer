package com.example.githubexplorer.data.remote.dto

import com.example.githubexplorer.data.Repo
import com.google.gson.annotations.SerializedName

data class RepoDto(
    val id: Int,
    val name: String,
    @SerializedName("html_url")
    val url: String,
    val description: String? = "",
    @SerializedName("stargazers_count")
    val stars: Int
)

fun RepoDto.toRepo(): Repo {
    return Repo(
        id = id,
        name = name,
        description = description ?: "No Description.",
        url = url,
        stars = stars
    )
}