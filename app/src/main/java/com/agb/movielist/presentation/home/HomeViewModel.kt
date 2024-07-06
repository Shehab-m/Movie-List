package com.agb.movielist.presentation.home

import android.util.Log
import com.agb.movielist.domain.model.MovieDetails
import com.agb.movielist.domain.usecase.GetPopularMoviesUseCase
import com.agb.movielist.domain.usecase.GetTopRatedMoviesUseCase
import com.agb.movielist.domain.utils.enums.MovieCategory
import com.agb.movielist.presentation.base.BaseViewModel
import com.agb.movielist.presentation.base.ErrorState
import com.agb.movielist.presentation.model.toSmallDetailsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : BaseViewModel<HomeUIState, HomeUIEffect>(HomeUIState()), HomeInteractionListener {


    init {
        getPopularMovies()
    }


    override fun onClickTopRated() {
        getTopRatedMovies()
    }

    override fun onClickPopular() {
        getPopularMovies()
    }

    override fun onClickSearch() {
        sendEffect(HomeUIEffect.NavigateToSearch)
    }

    override fun onClickRefresh() {
        when (state.value.selectedCategory) {
            MovieCategory.POPULAR -> getPopularMovies()
            MovieCategory.TOP_RATED -> getTopRatedMovies()
            MovieCategory.UNSPECIFIED -> getPopularMovies()
        }

    }

    override fun onClickItem(id: Int) {
        Log.d("onClickMovie: ", id.toString())
        sendEffect(HomeUIEffect.NavigateToMovie(id))
    }


    private fun getTopRatedMovies() {
        updateState {
            it.copy(
                isLoading = true,
                movies = emptyList(),
                isError = false,
                selectedCategory = MovieCategory.TOP_RATED
            )
        }
        tryToExecute(
            { getTopRatedMoviesUseCase() },
            ::onSuccessGetMovies,
            ::onError
        )
    }

    private fun getPopularMovies() {
        updateState {
            it.copy(
                isLoading = true,
                movies = emptyList(),
                isError = false,
                selectedCategory = MovieCategory.POPULAR
            )
        }
        tryToExecute(
            { getPopularMoviesUseCase() },
            ::onSuccessGetMovies,
            ::onError
        )
    }

    private fun onSuccessGetMovies(movies: List<MovieDetails>) {
        updateState {
            it.copy(
                isError = false,
                isLoading = false,
                movies = movies.map { it.toSmallDetailsUIState() })
        }
    }

    private fun onError(error: ErrorState) {
        Log.d("onError: ", error.toString())
        updateState { it.copy(isError = true, isLoading = false) }
    }


//    private fun onSuccessDate(dates: List<Date>) {
//        _state.update {
//            it.copy(dateItems = dates.map { date ->
//                date.toDateUiState { onClickDate(date) }
//            })
//        }
//        Log.i("onSuccessDate: ", _state.value.dateItems.toString())
//    }
//
//    private fun onClickDate(date: Date) {
//        getDateMatches(date)
//    }
//
//    private fun getDateMatches(date: Date) {
//        tryToExecute({
//            getFavoriteLeaguesMatchesByDateUseCase(date, "Africa/Cairo")
//        }, ::onSuccessFavourites, ::onError)
//    }
//
//
//    private fun onSuccessFavourites(f: Flow<List<Pair<League, List<Fixture>>>>) {
//        collectFlow(f) { pair ->
//            copy(
//                isLoading = false,
//                favoriteItems = pair.toHomeFavouriteUiState(
//                    onLeagueClick = ::onLeagueClicked,
//                    onMatchClick = ::onMatchClicked
//                )
//            )
//        }
//    }
//
//    private fun onError(e: Throwable) {
//        _state.update {
//            it.copy(errorMessage = e.localizedMessage ?: "Unknown error.", isLoading = false)
//        }
//        Log.d("TAG", e.message.toString())
//    }
//
//    private fun onMatchClicked(homeTeamId: Int, awayTeamId: Int, date: String) {
//        _event.update { Event(HomeEvents.MatchClickedEvent(homeTeamId, awayTeamId, date)) }
//    }
//
//    private fun onLeagueClicked(leagueId: Int, season: Int) {
//        _event.update { Event(HomeEvents.LeagueClickEvent(leagueId, season)) }
//    }


}