package com.agb.movielist.presentation.search

import androidx.paging.PagingData
import com.agb.movielist.presentation.model.MovieSmallDetailsUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

data class SearchUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val searchInput: MutableStateFlow<String> = MutableStateFlow(""),
    val errorMessage: String = "Error",
    val movies: Flow<PagingData<MovieSmallDetailsUIState>> = flowOf(),
) {
    val isDataVisible: Boolean = true && !isLoading
    val isResultEmpty: Boolean = false
}



