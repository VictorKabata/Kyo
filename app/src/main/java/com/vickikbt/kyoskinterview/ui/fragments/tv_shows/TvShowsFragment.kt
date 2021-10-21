package com.vickikbt.kyoskinterview.ui.fragments.tv_shows

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
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.kyoskinterview.R
import com.vickikbt.kyoskinterview.databinding.FragmentTvShowsBinding
import com.vickikbt.kyoskinterview.ui.adapters.InTheatersMoviesAdapter
import com.vickikbt.kyoskinterview.ui.adapters.MoviesShowsAdapter
import com.vickikbt.kyoskinterview.utils.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import kotlin.math.abs


class TvShowsFragment : Fragment(R.layout.fragment_tv_shows) {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvShowsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTvShowsBinding.bind(view)

        initUI()
    }

    private fun initUI() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.comingSoon.collect { uiState ->
                        when (uiState) {
                            is UiState.Loading -> {
                                //ToDo: Show loading state for in theater movies
                            }
                            is UiState.Error -> {
                                //ToDo: Show error message
                                Timber.e("Error: ${uiState.error}")
                            }
                            is UiState.Success -> {
                                initComingSoon(uiState.data)
                            }
                        }
                    }
                }

                launch {
                    viewModel.popularTvShows.collect { uiState ->
                        when (uiState) {
                            is UiState.Loading -> {
                                //ToDo: Show loading state for popular movies
                            }
                            is UiState.Error -> {
                                //ToDo: Show error message
                                Timber.e("Error: ${uiState.error}")
                            }
                            is UiState.Success -> {
                                initPopularTvShows(uiState.data)
                            }
                        }
                    }
                }

                launch {
                    viewModel.top250TvShows.collect { uiState ->
                        when (uiState) {
                            is UiState.Loading -> {
                                //ToDo: Show loading state for top 250 movies
                            }
                            is UiState.Error -> {
                                //ToDo: Show error message
                                Timber.e("Error: ${uiState.error}")
                            }
                            is UiState.Success -> {
                                initTop250TvShows(uiState.data)
                            }
                        }
                    }
                }

            }

        }
    }

    private fun initComingSoon(comingSoon: List<MovieShow>) {
        val viewPagerAdapter = InTheatersMoviesAdapter(comingSoon)

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(24))
        compositePageTransformer.addTransformer { page, position ->
            val transform = 1 - abs(position)
            page.scaleY = (0.85f + transform * 0.15f)
        }

        binding.viewPagerComingSoon.apply {
            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(compositePageTransformer)
            adapter = viewPagerAdapter
        }
    }

    private fun initPopularTvShows(popularTvShows: List<MovieShow>) {
        val adapter = MoviesShowsAdapter(popularTvShows) {
            navigateToDetails(it.id)
        }

        binding.recyclerviewPopular.adapter = adapter
    }

    private fun initTop250TvShows(tvShows: List<MovieShow>) {
        val adapter = MoviesShowsAdapter(tvShows) {
            navigateToDetails(it.id)
        }

        binding.recyclerviewTop250.adapter = adapter
    }

    private fun navigateToDetails(id: String) {
        val action = TvShowsFragmentDirections.tvShowsToDetails(id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}