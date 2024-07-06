package com.agb.movielist.presentation.base

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.agb.movielist.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.databinding.library.baseAdapters.BR

abstract class BaseFragment<VDB : ViewDataBinding, STATE, EFFECT> : Fragment() {
    @get:LayoutRes
    protected abstract val layoutIdFragment: Int
    protected abstract val viewModel: BaseViewModel<STATE, EFFECT>

    private lateinit var _binding: VDB
    protected val binding: VDB
        get() = _binding

    abstract fun onEvent(event: EFFECT)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutIdFragment, container, false)
        _binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectLatest { viewModel.effect.collectLatest { onEvent(it) } }
    }

    protected fun collectLatest(collect: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                collect()
            }
        }
    }

    protected fun changeStatusBarColor() {
        val statusBarColor = ContextCompat.getColor(requireContext(), R.color.md_theme_secondary)
        requireActivity().window.statusBarColor = statusBarColor
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.S)
    protected fun resetStatusBarColor() {
        val defaultStatusBarColor: Int = android.R.color.system_accent1_0
        requireActivity().window.statusBarColor = defaultStatusBarColor
    }

    protected fun showSnackBar(messages: String) {
        Snackbar.make(binding.root, messages, Snackbar.LENGTH_SHORT).show()
    }

}