package com.example.movieapplication.di

import com.example.movieapplication.repos.repository.ApiRepository
import com.example.movieapplication.repos.repositoryImpl.ApiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModuleBinds {

    @Binds
    abstract fun provideApiRepository(impl: ApiRepositoryImpl): ApiRepository
}