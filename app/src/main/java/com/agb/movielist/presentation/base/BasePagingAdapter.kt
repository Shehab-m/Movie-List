package com.agb.movielist.presentation.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagingAdapter<T : Any, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>,
    val listener: BaseInteractionListener,
) : PagingDataAdapter<T, VH>(diffCallback) {

    abstract val layoutID: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        createViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), layoutID, parent, false
            )
        )

    abstract fun createViewHolder(binding: ViewDataBinding): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        if (item != null) {
            bind(holder, item)
        }
    }

    abstract fun bind(holder: VH, item: T)

    open class ItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}

