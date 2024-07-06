package com.agb.movielist.presentation.search

import com.agb.movielist.presentation.base.BaseUiEffect

sealed class SearchUIEffect : BaseUiEffect {
    data class NavigateToMovie(val id: Int) : SearchUIEffect()
    data object NavigateBack : SearchUIEffect()
}
