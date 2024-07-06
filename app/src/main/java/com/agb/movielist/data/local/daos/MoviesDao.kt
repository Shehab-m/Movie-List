package com.agb.movielist.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agb.movielist.data.local.models.MovieDetailsLocalDto
import com.agb.movielist.domain.utils.enums.MovieCategory

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies_table WHERE id = :movieId")
    suspend fun getMovieByIdLocal(movieId: Int): MovieDetailsLocalDto?

    @Query("SELECT * FROM movies_table WHERE category = :category ")
    fun getMoviesByCategory(category: MovieCategory): List<MovieDetailsLocalDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieDetailsLocalDto>)

    @Query("DELETE FROM movies_table WHERE category = :category")
    suspend fun deleteMoviesByCategory(category: MovieCategory)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAllMoviesLocal()

}