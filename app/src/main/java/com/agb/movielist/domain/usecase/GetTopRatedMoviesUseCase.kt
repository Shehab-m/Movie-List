package com.agb.movielist.domain.usecase

import com.agb.movielist.domain.model.MovieDetails
import com.agb.movielist.domain.repository.IMoviesRepository
import com.agb.movielist.domain.utils.enums.MovieCategory
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {

    suspend operator fun invoke(): List<MovieDetails> {
        return try {
            repository.getTopRatedMovies()
        } catch (e: Exception) {
            val localMovies = repository.getMoviesByCategoryLocal(MovieCategory.TOP_RATED)
            localMovies.ifEmpty {
                throw e
            }
        }
    }

}