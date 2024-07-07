package com.agb.movielist.presentation.movieDetails

data class MovieDetailsUIState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val movie:MovieBigDetailsUIState = MovieBigDetailsUIState(),
)



