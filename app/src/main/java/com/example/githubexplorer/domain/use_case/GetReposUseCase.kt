package com.example.githubexplorer.domain.use_case

import com.example.githubexplorer.common.Resource
import com.example.githubexplorer.data.Repo
import com.example.githubexplorer.data.remote.dto.toRepo
import com.example.githubexplorer.domain.repository.RepoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetReposUseCase @Inject constructor(
    private val repository: RepoRepository
) {
    operator fun invoke(org: String): Flow<Resource<List<Repo>>> = flow {
        try {
            emit(Resource.Loading<List<Repo>>())
            val repos = repository.getReposByOrg(org).map { it.toRepo() }
            emit(Resource.Success<List<Repo>>(repos))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Repo>>(e.localizedMessage ?: "There was an error."))
        } catch(e: IOException) {
            emit(Resource.Error<List<Repo>>("A fatal error occurred."))
        }
    }
}