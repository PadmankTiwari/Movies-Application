package com.example.movieapplication.di

import com.example.movieapplication.data.session.SessionManager
import com.example.movieapplication.data.session.SessionManagerImpl
import com.example.movieapplication.database.UserDatabase
import com.example.movieapplication.database.UserDatabaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class ApplicationModuleBinds {
    @Singleton
    @Binds
    abstract fun provideSessionManager(impl: SessionManagerImpl): SessionManager

    @Singleton
    @Binds
    abstract fun provideUserDatabase(impl: UserDatabaseImpl): UserDatabase
}