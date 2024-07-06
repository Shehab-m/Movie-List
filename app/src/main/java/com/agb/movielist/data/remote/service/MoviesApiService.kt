package com.agb.movielist.data.remote.service

import com.agb.movielist.data.remote.model.BasePagingResponse
import com.agb.movielist.data.remote.model.MovieDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<BasePagingResponse<MovieDetailsDto>>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<BasePagingResponse<MovieDetailsDto>>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailsById(@Path("movie_id") movieId: Int): Response<MovieDetailsDto>

    @GET("search/movie")
    suspend fun searchMoviesByKeyword(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): Response<BasePagingResponse<MovieDetailsDto>>

}

