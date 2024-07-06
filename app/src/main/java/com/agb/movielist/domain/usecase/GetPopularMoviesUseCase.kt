package com.agb.movielist.domain.usecase

import android.util.Log
import com.agb.movielist.domain.model.MovieDetails
import com.agb.movielist.domain.repository.IMoviesRepository
import com.agb.movielist.domain.utils.enums.MovieCategory
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {

    suspend operator fun invoke(): List<MovieDetails> {
        return try {
            Log.d( "invoke Popular: ", "success")
            repository.getPopularMovies()
        } catch (e: Exception) {
            Log.d( "invoke Popular: ", "catch")
            val localMovies = repository.getMoviesByCategoryLocal(MovieCategory.POPULAR)
            localMovies.ifEmpty {
                Log.d( "invoke Popular: ", "ifEmpty")
                Log.d( "invoke Popular: ", e.toString())
                throw e
            }
        }
    }

}