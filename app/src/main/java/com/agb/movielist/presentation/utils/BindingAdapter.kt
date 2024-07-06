package com.agb.movielist.presentation.utils

import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agb.movielist.R
import com.agb.movielist.presentation.base.BaseAdapter
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip

@BindingAdapter("app:imageUrl")
fun ImageView.setImageFromUrl(imagePath: String?) {
    val path = imagePath.takeIf { !it.isNullOrEmpty() && !it.contains("image_not_available") }
        ?: R.drawable.no_image
    val imageUrl = "https://image.tmdb.org/t/p/w500" + path
    Log.d("setImageFromUrl: ", imageUrl)
    Glide.with(this)
        .load(imageUrl)
        .placeholder(R.drawable.no_image)
        .error(R.drawable.no_image)
        .centerCrop()
        .into(this)
}

object BindingAdapters {
    @BindingAdapter("app:textWatcher")
    @JvmStatic
    fun setTextWatcher(editText: EditText, textWatcher: TextWatcher?) {
        if (textWatcher != null) {
            editText.addTextChangedListener(textWatcher)
        }
    }
}

@BindingAdapter(value = ["app:items"])
fun <T> RecyclerView.setRecyclerItems(items: List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
    smoothScrollToPosition(0)
}

@BindingAdapter("app:isVisible")
fun isVisible(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}

@BindingAdapter("app:isChecked")
fun Chip.isChecked(checked: Boolean) {
    this.isChecked = checked
}