package com.agb.movielist.presentation.search

import com.agb.movielist.presentation.model.MovieSmallDetailsUIState
import kotlinx.coroutines.flow.MutableStateFlow

data class SearchUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val searchInput: MutableStateFlow<String> = MutableStateFlow(""),
    val errorMessage: String = "Error",
    val movies: List<MovieSmallDetailsUIState> = emptyList(),
) {
    val isDataVisible: Boolean = true && !isLoading
    val isResultEmpty: Boolean = false
}



