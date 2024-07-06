package com.agb.movielist.presentation.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.agb.movielist.R
import com.agb.movielist.presentation.base.BaseAdapter
import com.agb.movielist.presentation.search.SearchViewModel
import com.bumptech.glide.Glide

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

//@BindingAdapter("app:onSearchTextChanged")
//fun EditText.onSearchTextChanged(onTextChanged: (String) -> Unit) {
//    addTextChangedListener(object : TextWatcher {
//        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            onTextChanged(s.toString().trim())
//        }
//
//        override fun afterTextChanged(s: Editable?) {}
//    })
//}

object BindingAdapters {
    @BindingAdapter("app:textWatcher")
    @JvmStatic
    fun setTextWatcher(editText: EditText, textWatcher: TextWatcher?) {
        if (textWatcher != null) {
            editText.addTextChangedListener(textWatcher)
        }
    }
}

//@BindingAdapter("app:onSearchTextChanged")
//fun setTextWatcher(editText: EditText, textWatcher: TextWatcher?) {
//    if (textWatcher != null) {
//        editText.addTextChangedListener(textWatcher)
//    }
//}


@BindingAdapter(value = ["app:items"])
fun <T> RecyclerView.setRecyclerItems(items: List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
    smoothScrollToPosition(0)
}

@BindingAdapter(value = ["app:showLoading"])
fun showLoading(view: ProgressBar, isShowing: Boolean) {
    view.isVisible = isShowing
}

@BindingAdapter("app:chipGroupVisibility")
fun chipGroupVisibility(horizontalScrollView: HorizontalScrollView, isVisible: Boolean) {
    horizontalScrollView.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:isVisible")
fun isVisible(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}