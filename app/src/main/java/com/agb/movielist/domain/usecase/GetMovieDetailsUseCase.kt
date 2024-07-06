package com.agb.movielist.domain.usecase

import com.agb.movielist.domain.model.MovieDetails
import com.agb.movielist.domain.repository.IMoviesRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {

    suspend operator fun invoke(id: Int): MovieDetails {
        return try {
            repository.getMovieDetailsById(id)
        } catch (e: Exception) {
            repository.getMovieByIdLocal(id) ?: throw e
        }
    }

}