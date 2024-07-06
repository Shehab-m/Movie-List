package com.agb.movielist.presentation.home

import com.agb.movielist.R
import com.agb.movielist.presentation.base.BaseAdapter
import com.agb.movielist.presentation.model.MovieSmallDetailsUIState

class HomeAdapter(
    itemsHome: MutableList<MovieSmallDetailsUIState>,
    listener: HomeInteractionListener,
) : BaseAdapter<MovieSmallDetailsUIState>(itemsHome, listener) {
    override val layoutID = R.layout.item_movie_grid
}