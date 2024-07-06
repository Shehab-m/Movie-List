package com.agb.movielist.data.repository

import android.util.Log
import com.agb.movielist.data.local.daos.MoviesDao
import com.agb.movielist.data.remote.model.BasePagingResponse
import com.agb.movielist.data.remote.service.MoviesApiService
import com.agb.movielist.data.repository.mapper.toEntity
import com.agb.movielist.data.repository.mapper.toLocalData
import com.agb.movielist.domain.model.MovieDetails
import com.agb.movielist.domain.repository.IMoviesRepository
import com.agb.movielist.domain.utils.enums.MovieCategory
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val apiService: MoviesApiService,
    private val localDataSource: MoviesDao,
) : IMoviesRepository {

    override suspend fun getPopularMovies(): List<MovieDetails> {
        return wrapBasePagingResponse { apiService.getPopularMovies() }.map { it.toEntity(MovieCategory.POPULAR) }
    }

    override suspend fun getTopRatedMovies(): List<MovieDetails> {
        return wrapBasePagingResponse { apiService.getTopRatedMovies() }.map { it.toEntity(MovieCategory.TOP_RATED) }
    }

    override suspend fun getMovieDetailsById(id: Int): MovieDetails {
        return wrapResponse { apiService.getMovieDetailsById(id) }.toEntity()
    }

    override suspend fun searchMoviesByKeyword(query: String): List<MovieDetails>{
        return wrapBasePagingResponse { apiService.searchMoviesByKeyword(query,1) }.map { it.toEntity() }
    }

    override suspend fun getMoviesByCategoryLocal(category: MovieCategory): List<MovieDetails> {
        return localDataSource.getMoviesByCategory(category).map { it.toEntity() }
    }

    override suspend fun getMovieByIdLocal(movieId: Int): MovieDetails? {
        return localDataSource.getMovieByIdLocal(movieId)?.toEntity()
    }

    override suspend fun insertMoviesLocal(movies: List<MovieDetails>) {
        localDataSource.insertMovies(movies.map { it.toLocalData() })
    }

    override suspend fun deleteMoviesByCategoryLocal(category: MovieCategory) {
        localDataSource.deleteMoviesByCategory(category)
    }


    override suspend fun deleteAllMoviesLocal() {
        localDataSource.deleteAllMoviesLocal()
    }

    private suspend fun <T> wrapBasePagingResponse(
        function: suspend () -> Response<BasePagingResponse<T>>
    ): List<T> {
        return try {
            val apiResponse = function()
            if (apiResponse.isSuccessful) {
                val responseBody = apiResponse.body()
                val results = responseBody?.results
                Log.d("Tag", "response Success: ${results}")
                responseBody?.results ?: throw Exception("Data not found!")
            } else {
                val message = apiResponse.message()
                Log.d("Tag", "response Not Success:: ${message}")
                throw Exception(message)
            }
        } catch (e: Exception) {
            Log.e("Tag", "response Error:${e.message}")
            throw Exception("${e.message}")
        }
    }

    private suspend fun <T> wrapResponse(
        function: suspend () -> Response<T>
    ): T {
        return try {
            val apiResponse = function()
            if (apiResponse.isSuccessful) {
                val responseBody = apiResponse.body()
                Log.d("Tag", "response Success: ${responseBody}")
                responseBody ?: throw Exception("Data not found!")
            } else {
                val message = apiResponse.message()
                Log.d("Tag", "response Not Success:: ${message}")
                throw Exception(message)
            }
        } catch (e: Exception) {
            Log.e("Tag", "response Error:${e.message}")
            throw Exception("${e.message}")
        }
    }
}