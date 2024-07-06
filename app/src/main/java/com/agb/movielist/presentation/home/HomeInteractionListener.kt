package com.agb.movielist.presentation.home

import com.agb.movielist.presentation.base.BaseInteractionListener

interface HomeInteractionListener : BaseInteractionListener {
    fun onClickTopRated()
    fun onClickPopular()
    fun onClickSearch()
    fun onClickRefresh()
}