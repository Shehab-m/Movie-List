package com.agb.movielist.presentation.model

import com.agb.movielist.domain.model.MovieDetails
import com.agb.movielist.domain.utils.enums.MovieCategory

data class MovieSmallDetailsUIState(
    val id: Int = 0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val category: MovieCategory = MovieCategory.UNSPECIFIED
)

fun MovieDetails.toSmallDetailsUIState(): MovieSmallDetailsUIState {
    return MovieSmallDetailsUIState(
        id = id,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        category = category
    )
}


