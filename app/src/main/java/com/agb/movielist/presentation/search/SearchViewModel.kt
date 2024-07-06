package com.agb.movielist.presentation.search

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.agb.movielist.domain.model.MovieDetails
import com.agb.movielist.domain.usecase.SearchMoviesUseCase
import com.agb.movielist.presentation.base.BaseViewModel
import com.agb.movielist.presentation.base.ErrorState
import com.agb.movielist.presentation.model.toSmallDetailsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
) : BaseViewModel<SearchUIState, SearchUIEffect>(SearchUIState()), SearchInteractionListener {

    init {
        observeSearchQuery()
    }

    val searchFieldWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            state.value.searchInput.update { s.toString() }
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        collectFlow(state.value.searchInput.debounce(300).distinctUntilChanged()) { query ->
            if (query.isNotEmpty()) {
                updateState { it.copy(isLoading = true, isError = false) }
                searchMovies(query)
            }
            this
        }
    }

    private fun searchMovies(query: String) {
        updateState {
            it.copy(
                isLoading = true,
                movies = emptyList(),
                isError = false,
            )
        }
        tryToExecute(
            {
                searchMoviesUseCase(query)
            },
            ::onSuccessSearch,
            ::onError
        )
    }

    private fun onSuccessSearch(movies: List<MovieDetails>) {
        updateState {
            it.copy(isLoading = false, movies = movies.map { it.toSmallDetailsUIState() })
        }
    }

    private fun onError(error: ErrorState) {
        Log.d("onError: ", error.toString())
        updateState { it.copy(isError = true, isLoading = false) }
    }

    override fun onClickItem(id: Int) {
        Log.d("onClickMovie: ", id.toString())
        sendEffect(SearchUIEffect.NavigateToMovie(id))
    }

    override fun onClickRefresh() {
        searchMovies(_state.value.searchInput.value)
    }

    override fun onClickBack() {
        sendEffect(SearchUIEffect.NavigateBack)
    }

}