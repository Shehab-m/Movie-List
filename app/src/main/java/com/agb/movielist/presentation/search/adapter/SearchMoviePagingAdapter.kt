package com.agb.movielist.presentation.search.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.agb.movielist.R
import com.agb.movielist.presentation.base.BasePagingAdapter
import com.agb.movielist.presentation.model.MovieSmallDetailsUIState
import androidx.databinding.library.baseAdapters.BR
import com.agb.movielist.presentation.base.BaseInteractionListener

class SearchMoviePagingAdapter(
    listener: BaseInteractionListener
) : BasePagingAdapter<MovieSmallDetailsUIState, SearchMoviePagingAdapter.MovieViewHolder>(
    DIFF_CALLBACK,
    listener
) {

    override val layoutID: Int
        get() = R.layout.item_movie_grid

    override fun createViewHolder(binding: ViewDataBinding): MovieViewHolder {
        return MovieViewHolder(binding)
    }

    override fun bind(holder: MovieViewHolder, item: MovieSmallDetailsUIState) {
        holder.binding.apply {
            setVariable(BR.item, item)
            setVariable(BR.listener, listener)
            executePendingBindings()
        }
    }

    class MovieViewHolder(binding: ViewDataBinding) : ItemViewHolder(binding)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieSmallDetailsUIState>() {
            override fun areItemsTheSame(
                oldItem: MovieSmallDetailsUIState,
                newItem: MovieSmallDetailsUIState
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieSmallDetailsUIState,
                newItem: MovieSmallDetailsUIState
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
