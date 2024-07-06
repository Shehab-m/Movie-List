package com.agb.movielist.presentation.movieDetails

import com.agb.movielist.domain.model.MovieDetails

data class MovieBigDetailsUIState (
    val id: Int = 0,
    val title: String = "",
    val posterPath: String = "",
    val releaseDate: String = "No Data",
    val overview: String = "No Data",
)

fun MovieDetails.toBigDetailsUIState(): MovieBigDetailsUIState {
    return MovieBigDetailsUIState(
        id = id,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        overview = overview
    )
}
