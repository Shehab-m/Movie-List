package com.agb.movielist.data.repository.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.agb.movielist.data.remote.service.MoviesApiService
import com.agb.movielist.data.repository.mapper.toEntity
import com.agb.movielist.domain.model.MovieDetails
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(
    private val service: MoviesApiService,
    private val query: String
) : PagingSource<Int, MovieDetails>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetails> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.searchMoviesByKeyword(query, page)
            val movies = response.body()?.results?.map { it.toEntity() } ?: emptyList()
            val nextKey = if (movies.isEmpty()) null else page + 1
            val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
            LoadResult.Page(
                data = movies,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDetails>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}