package com.agb.movielist.domain.usecase

import com.agb.movielist.domain.model.MovieDetails
import com.agb.movielist.domain.repository.IMoviesRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {
    suspend operator fun invoke(keyword: String): List<MovieDetails>{
        return repository.searchMoviesByKeyword(keyword)
    }

}