package com.agb.movielist.presentation.home

import com.agb.movielist.domain.utils.enums.MovieCategory
import com.agb.movielist.presentation.model.MovieSmallDetailsUIState

data class HomeUIState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val errorMessage: String = "Error",
    val movies: List<MovieSmallDetailsUIState> = emptyList(),
    val selectedCategory: MovieCategory = MovieCategory.POPULAR
) {
    val isDataVisible: Boolean = movies.isNotEmpty() && !isLoading
}



