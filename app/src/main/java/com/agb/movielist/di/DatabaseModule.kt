package com.agb.movielist.di

import android.content.Context
import androidx.room.Room
import com.agb.movielist.data.local.daos.MoviesDao
import com.agb.movielist.data.local.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMoviesDatabase(@ApplicationContext context: Context): MoviesDatabase =
        Room.databaseBuilder(context, MoviesDatabase::class.java, "MOVIES_DATABASE").build()

    @Singleton
    @Provides
    fun provideMoviesDoa(moviesDatabase: MoviesDatabase): MoviesDao =
        moviesDatabase.getMoviesDao()


}
