package com.vickikbt.kyoskinterview.ui.fragments.tv_shows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.kyoskinterview.R
import com.vickikbt.kyoskinterview.databinding.FragmentTvShowsBinding
import com.vickikbt.kyoskinterview.ui.adapters.InTheatersMoviesAdapter
import com.vickikbt.kyoskinterview.ui.adapters.MoviesShowsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
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
        viewModel.comingSoon.observe(viewLifecycleOwner) { comingSoon ->
            initInTheatersMovies(comingSoon)
        }

        viewModel.popularTvShows.observe(viewLifecycleOwner) { popularTvShows ->
            initPopularMovies(popularTvShows)
        }

        viewModel.top250TvShows.observe(viewLifecycleOwner) { top250TvShows ->
            initTop250Movies(top250TvShows)
        }
    }

    private fun initInTheatersMovies(comingSoon: List<MovieShow>) {
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

    private fun initPopularMovies(popularTvShows: List<MovieShow>) {
        val adapter = MoviesShowsAdapter(popularTvShows) {
            navigateToDetails(it.id)
        }

        binding.recyclerviewPopular.adapter = adapter
    }

    private fun initTop250Movies(tvShows: List<MovieShow>) {
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