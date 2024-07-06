package com.agb.movielist.presentation.search

import com.agb.movielist.presentation.base.BaseInteractionListener

interface SearchInteractionListener : BaseInteractionListener {
    fun onClickRefresh()
    fun onClickBack()
}