package com.agb.movielist.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.agb.movielist.data.local.converters.Converters
import com.agb.movielist.data.local.daos.MoviesDao
import com.agb.movielist.data.local.models.MovieDetailsLocalDto

@Database(
    entities = [MovieDetailsLocalDto::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

}
