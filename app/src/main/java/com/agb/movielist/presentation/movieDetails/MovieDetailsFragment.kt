package com.agb.movielist.presentation.movieDetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agb.movielist.R
import com.agb.movielist.databinding.FragmentMovieDetailsBinding
import com.agb.movielist.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding, MovieDetailsUIState, MovieDetailsUIEffect>() {

    override val layoutIdFragment = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onEvent(event: MovieDetailsUIEffect) {
        when (event) {
            is MovieDetailsUIEffect.NavigateToMovie -> {

            }
            MovieDetailsUIEffect.NavigateBack -> {
                findNavController().navigateUp()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar.let {
            it?.hide()
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar.let {
            it?.show()
        }
    }
}