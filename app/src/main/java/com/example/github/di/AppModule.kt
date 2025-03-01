package com.example.github.di

import com.example.github.data.api.GithubService
import com.example.github.data.repository.PullRequestRepositoryImpl
import com.example.github.domain.repository.PullRequestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Provides
    fun provideBaseUrl() = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String) : Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGitHubService(retrofit: Retrofit): GithubService =
        retrofit.create(GithubService::class.java)

    @Provides
    @Singleton
    fun providePullRequestRepository(api: GithubService): PullRequestRepository =
        PullRequestRepositoryImpl(api)

}