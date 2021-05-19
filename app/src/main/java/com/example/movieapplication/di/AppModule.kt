package com.example.movieapplication.di

import android.content.Context
import androidx.room.Room
import com.example.movieapplication.BuildConfig
import com.example.movieapplication.database.Database
import com.example.movieapplication.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, BuildConfig.DATABASE)
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideStateDao(database: Database): UserDao = database.userDao()

}
