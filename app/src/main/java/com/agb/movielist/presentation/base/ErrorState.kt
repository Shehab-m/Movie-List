package com.agb.movielist.presentation.base

sealed interface ErrorState {
    data object NoInternet : ErrorState
    data class UnknownError(val errorMessage: String) : ErrorState
}

