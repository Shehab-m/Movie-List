package com.agb.movielist.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agb.movielist.R
import com.agb.movielist.databinding.FragmentHomeBinding
import com.agb.movielist.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeUIState, HomeUIEffect>() {

    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        collectChange()
    }

    private fun setAdapter() {
        homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerViewHome.adapter = homeAdapter
    }

    private fun collectChange() {
        binding.recyclerViewHome.smoothScrollToPosition(0)
        collectLatest {
            viewModel.state.collect{ state ->
                homeAdapter.setItems(state.movies)
            }
        }
    }

    override fun onEvent(event: HomeUIEffect) {
        when (event) {
            is HomeUIEffect.NavigateToMovie -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(event.id)
                )
            }
            HomeUIEffect.NavigateToSearch -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToSearchFragment()
                )
            }
        }
    }
}