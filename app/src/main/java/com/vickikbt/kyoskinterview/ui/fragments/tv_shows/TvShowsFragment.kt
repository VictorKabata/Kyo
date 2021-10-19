package com.vickikbt.kyoskinterview.ui.fragments.tv_shows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.vickikbt.domain.models.InTheatersComingSoon
import com.vickikbt.domain.models.PopularMovieShow
import com.vickikbt.domain.models.Top250MovieShow
import com.vickikbt.kyoskinterview.R
import com.vickikbt.kyoskinterview.databinding.FragmentTvShowsBinding
import com.vickikbt.kyoskinterview.ui.adapters.InTheatersMoviesAdapter
import com.vickikbt.kyoskinterview.ui.adapters.PopularMoviesAdapter
import com.vickikbt.kyoskinterview.ui.adapters.Top250MoviesAdapter
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

    private fun initInTheatersMovies(comingSoon: List<InTheatersComingSoon>) {
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

    private fun initPopularMovies(popularTvShows: List<PopularMovieShow>) {
        binding.recyclerviewPopular.apply {
            adapter = PopularMoviesAdapter(popularTvShows)
        }
    }

    private fun initTop250Movies(top250TvShows: List<Top250MovieShow>) {
        binding.recyclerviewTop250.apply {
            adapter = Top250MoviesAdapter(top250TvShows)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}