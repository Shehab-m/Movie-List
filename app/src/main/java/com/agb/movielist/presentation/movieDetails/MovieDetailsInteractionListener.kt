package com.agb.movielist.presentation.movieDetails

import com.agb.movielist.presentation.base.BaseInteractionListener

interface MovieDetailsInteractionListener : BaseInteractionListener {
    fun onClickRefresh()
    fun onClickBack()
}