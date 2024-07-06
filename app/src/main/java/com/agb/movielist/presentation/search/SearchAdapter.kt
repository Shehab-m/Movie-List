package com.agb.movielist.presentation.search

import com.agb.movielist.R
import com.agb.movielist.presentation.base.BaseAdapter
import com.agb.movielist.presentation.model.MovieSmallDetailsUIState

class SearchAdapter(
    itemsSearch: MutableList<MovieSmallDetailsUIState>,
    listener: SearchInteractionListener,
) : BaseAdapter<MovieSmallDetailsUIState>(itemsSearch, listener) {
    override val layoutID = R.layout.item_movie_grid
}