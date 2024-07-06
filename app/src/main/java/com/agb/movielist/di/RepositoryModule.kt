package com.agb.movielist.di

import com.agb.movielist.domain.repository.IMoviesRepository
import com.agb.movielist.data.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMoviesRepository(repository: MoviesRepository): IMoviesRepository
}