package com.agb.movielist.presentation.movieDetails

import com.agb.movielist.presentation.base.BaseUiEffect

sealed class MovieDetailsUIEffect : BaseUiEffect {
    data class NavigateToMovie(val id: Int) : MovieDetailsUIEffect()
    data object NavigateBack : MovieDetailsUIEffect()
}
