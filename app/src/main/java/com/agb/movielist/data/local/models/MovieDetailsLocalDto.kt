package com.agb.movielist.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agb.movielist.domain.utils.enums.MovieCategory

@Entity(tableName = "movies_table")
data class MovieDetailsLocalDto(
    @PrimaryKey val id: Int,
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