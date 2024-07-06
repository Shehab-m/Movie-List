package com.agb.movielist.domain.model

import com.agb.movielist.domain.utils.enums.MovieCategory

data class MovieDetails(
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val category: MovieCategory
)