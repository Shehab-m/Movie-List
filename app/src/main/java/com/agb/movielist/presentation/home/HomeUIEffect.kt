package com.agb.movielist.presentation.home

import com.agb.movielist.presentation.base.BaseUiEffect

sealed class HomeUIEffect : BaseUiEffect {
    data object NavigateToSearch : HomeUIEffect()
    data class NavigateToMovie(val id: Int) : HomeUIEffect()
}
