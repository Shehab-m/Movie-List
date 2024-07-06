package com.agb.movielist.presentation.movieDetails

data class MovieDetailsUIState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val movie:MovieBigDetailsUIState = MovieBigDetailsUIState(),
) {
    val isDataVisible: Boolean = movie.id != 0 && !isLoading
}



