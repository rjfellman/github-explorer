package com.example.githubexplorer.di

import com.example.githubexplorer.common.Constants
import com.example.githubexplorer.data.remote.GithubApi
import com.example.githubexplorer.data.repository.RepoRepositoryImpl
import com.example.githubexplorer.domain.repository.RepoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGithubApi(): GithubApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepoRepository(api: GithubApi): RepoRepository {
        return RepoRepositoryImpl(api)
    }
}