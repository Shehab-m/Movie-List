package com.agb.movielist.data.repository.mapper

import com.agb.movielist.data.local.models.MovieDetailsLocalDto
import com.agb.movielist.data.remote.model.MovieDetailsDto
import com.agb.movielist.domain.model.MovieDetails
import com.agb.movielist.domain.utils.enums.MovieCategory

fun MovieDetailsDto.toEntity(category: MovieCategory = MovieCategory.UNSPECIFIED): MovieDetails {
    return MovieDetails(
        id = id ?: 0,
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        title = title ?: "",
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
        category = category,
    )
}

fun MovieDetailsLocalDto.toEntity(): MovieDetails {
    return MovieDetails(
        id = id,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        category = category,
    )
}

fun MovieDetails.toLocalData(): MovieDetailsLocalDto {
    return MovieDetailsLocalDto(
        id = id,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        category = category
    )
}
