package com.agb.movielist.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agb.movielist.R
import com.agb.movielist.databinding.FragmentSearchBinding
import com.agb.movielist.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchUIState, SearchUIEffect>() {

    override val layoutIdFragment = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()

    private lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        collectChange()
        setSearchFocus()
    }

    private fun setAdapter() {
        searchAdapter = SearchAdapter(mutableListOf(), viewModel)
        binding.recyclerViewSearch.adapter = searchAdapter
    }

    private fun collectChange() {
        binding.recyclerViewSearch.smoothScrollToPosition(0)
        collectLatest {
            viewModel.state.collect{ state ->
                searchAdapter.setItems(state.movies)
            }
        }
    }

    override fun onEvent(event: SearchUIEffect) {
        when (event) {
            is SearchUIEffect.NavigateToMovie -> {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(event.id)
                )
            }
            SearchUIEffect.NavigateBack -> {
                findNavController().navigateUp()
            }
        }

    }

    private fun setSearchFocus() {
        val searchEditText = binding.editTextSearch
        searchEditText.requestFocus()
        val inputManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT)
    }
}