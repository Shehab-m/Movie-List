package com.agb.movielist.domain.repository

import com.agb.movielist.domain.model.MovieDetails
import com.agb.movielist.domain.utils.enums.MovieCategory

interface IMoviesRepository {

    suspend fun getPopularMovies(): List<MovieDetails>

    suspend fun getTopRatedMovies(): List<MovieDetails>

    suspend fun getMoviesByCategoryLocal(category: MovieCategory): List<MovieDetails>

    suspend fun getMovieByIdLocal(movieId: Int): MovieDetails?

    suspend fun insertMoviesLocal(movies: List<MovieDetails>)

    suspend fun deleteMoviesByCategoryLocal(category: MovieCategory)

    suspend fun deleteAllMoviesLocal()

    suspend fun getMovieDetailsById(id:Int): MovieDetails

    suspend fun searchMoviesByKeyword(query: String): List<MovieDetails>
}