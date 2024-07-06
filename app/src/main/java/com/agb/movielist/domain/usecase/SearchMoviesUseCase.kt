package com.agb.movielist.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import com.agb.movielist.domain.model.MovieDetails
import com.agb.movielist.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {
    suspend operator fun invoke(keyword: String): Flow<PagingData<MovieDetails>> {
        Log.d( "Search Paging: ","USECASE")
        return repository.searchMoviesByKeyword(keyword)
    }

}