package com.vickikbt.kyoskinterview.ui.fragments.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.vickikbt.commons.Constants
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.kyoskinterview.R
import com.vickikbt.kyoskinterview.databinding.FragmentMoviesBinding
import com.vickikbt.kyoskinterview.ui.adapters.MovieShowViewPagerAdapter
import com.vickikbt.kyoskinterview.ui.adapters.MoviesShowsRecyclerviewAdapter
import com.vickikbt.kyoskinterview.utils.UiState
import com.vickikbt.kyoskinterview.utils.toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import kotlin.math.abs

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesBinding.bind(view)

        initUI()

    }

    private fun initUI() {
        binding.linearLayoutPopular.setOnClickListener { navigateToAllContent(Constants.POPULAR_MOVIE) }
        binding.linearLayoutTop250.setOnClickListener { navigateToAllContent(Constants.TOP_250_MOVIE) }

        binding.toolbarMovies.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_search) {
                requireContext().toast("Under development")
                true
            }
            false
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.inTheatersMovies.collect { uiState ->
                        when (uiState) {
                            is UiState.Loading -> {
                                //ToDo: Show loading state for in theater movies
                            }
                            is UiState.Error -> {
                                //ToDo: Show error message
                                Timber.e("Error: ${uiState.error}")
                            }
                            is UiState.Success -> {
                                initInTheatersMovies(uiState.data)
                            }
                        }
                    }
                }

                launch {
                    viewModel.popularMovies.collect { uiState ->
                        when (uiState) {
                            is UiState.Loading -> {
                                //ToDo: Show loading state for popular movies
                            }
                            is UiState.Error -> {
                                //ToDo: Show error message
                                Timber.e("Error: ${uiState.error}")
                            }
                            is UiState.Success -> {
                                initPopularMovies(uiState.data)
                            }
                        }
                    }
                }

                launch {
                    viewModel.top250Movies.collect { uiState ->
                        when (uiState) {
                            is UiState.Loading -> {
                                //ToDo: Show loading state for top 250 movies
                            }
                            is UiState.Error -> {
                                //ToDo: Show error message
                                Timber.e("Error: ${uiState.error}")
                            }
                            is UiState.Success -> {
                                initTop250Movies(uiState.data)
                            }
                        }
                    }
                }

            }

        }
    }

    private fun initInTheatersMovies(inTheatersMovies: List<MovieShow>) {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(24))
        compositePageTransformer.addTransformer { page, position ->
            val transform = 1 - abs(position)
            page.scaleY = (0.85f + transform * 0.15f)
        }

        if (!inTheatersMovies.isNullOrEmpty()) {
            val viewPagerAdapter = MovieShowViewPagerAdapter(inTheatersMovies) {
                navigateToDetails(it.id)
            }
            binding.viewPagerInTheaters.apply {
                offscreenPageLimit = 3
                clipToPadding = false
                clipChildren = false
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                setPageTransformer(compositePageTransformer)
                adapter = viewPagerAdapter
            }
        }
    }

    private fun initPopularMovies(popularMovies: List<MovieShow>) {
        if (!popularMovies.isNullOrEmpty()) {
            val adapter = MoviesShowsRecyclerviewAdapter(popularMovies) {
                navigateToDetails(it.id)
            }

            binding.recyclerviewPopular.adapter = adapter
        }
    }

    private fun initTop250Movies(movies: List<MovieShow>) {
        if (!movies.isNullOrEmpty()) {
            val adapter = MoviesShowsRecyclerviewAdapter(movies) {
                navigateToDetails(it.id)
            }
            binding.recyclerviewTop250.adapter = adapter
        }
    }

    private fun navigateToDetails(id: String) {
        val action = MoviesFragmentDirections.moviesToDetails(id)
        findNavController().navigate(action)
    }

    private fun navigateToAllContent(category: String) {
        val action = MoviesFragmentDirections.moviesToAllContent(category)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}