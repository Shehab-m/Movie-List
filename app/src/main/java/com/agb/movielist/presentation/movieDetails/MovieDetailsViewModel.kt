package com.agb.movielist.presentation.movieDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.agb.movielist.domain.model.MovieDetails
import com.agb.movielist.domain.usecase.GetMovieDetailsUseCase
import com.agb.movielist.domain.utils.enums.MovieCategory
import com.agb.movielist.presentation.base.BaseViewModel
import com.agb.movielist.presentation.base.ErrorState
import com.agb.movielist.presentation.model.toSmallDetailsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    state: SavedStateHandle
) : BaseViewModel<MovieDetailsUIState, MovieDetailsUIEffect>(MovieDetailsUIState()),
    MovieDetailsInteractionListener {

    private val movieDetailsArgs = MovieDetailsFragmentArgs.fromSavedStateHandle(state)

    init {
        getMovieDetails()
    }

    private fun getMovieDetails(){
        updateState {
            it.copy(
                isLoading = true,
                isError = false,
            )
        }
        tryToExecute(
            {getMovieDetailsUseCase(movieDetailsArgs.id)},
            ::onSuccessGetMovieDetails,
            ::onError
        )
    }

    private fun onSuccessGetMovieDetails(movie: MovieDetails) {
        updateState {
            it.copy(
                isLoading = false,
                movie = movie.toBigDetailsUIState()
            )
        }
    }

    private fun onError(error: ErrorState) {
        Log.d("onError: ", error.toString())
        updateState { it.copy(isError = true, isLoading = false) }
    }

    override fun onClickItem(id: Int) {
        Log.d("onClickMovie: ", id.toString())
    }

    override fun onClickRefresh() {
        getMovieDetails()
    }

    override fun onClickBack() {
        sendEffect(MovieDetailsUIEffect.NavigateBack)
    }

}