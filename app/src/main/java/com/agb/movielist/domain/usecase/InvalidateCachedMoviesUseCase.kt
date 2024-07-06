package com.agb.movielist.domain.usecase

import com.agb.movielist.domain.repository.IMoviesRepository
import javax.inject.Inject

class InvalidateCachedMoviesUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {

    suspend operator fun invoke(): Boolean {
        val popularMovies = repository.getPopularMovies()
        val topRatedMovies = repository.getTopRatedMovies()
        val movies = popularMovies + topRatedMovies
        repository.deleteAllMoviesLocal()
        repository.insertMoviesLocal(movies)
        return true
    }

}