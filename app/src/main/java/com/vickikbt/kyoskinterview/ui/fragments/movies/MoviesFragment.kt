package com.vickikbt.kyoskinterview.ui.fragments.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.vickikbt.domain.models.InTheatersComingSoonMovie
import com.vickikbt.domain.models.PopularMovieShow
import com.vickikbt.domain.models.Top250MovieShow
import com.vickikbt.kyoskinterview.R
import com.vickikbt.kyoskinterview.databinding.FragmentMoviesBinding
import com.vickikbt.kyoskinterview.ui.adapters.InTheatersMoviesAdapter
import com.vickikbt.kyoskinterview.ui.adapters.PopularMoviesAdapter
import com.vickikbt.kyoskinterview.ui.adapters.Top250MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
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
        viewModel.inTheatersMovies.observe(viewLifecycleOwner) { inTheatersMovies ->
            initInTheatersMovies(inTheatersMovies)
        }

        viewModel.popularMovies.observe(viewLifecycleOwner) { popularMovies ->
            initPopularMovies(popularMovies)
        }

        viewModel.top250Movies.observe(viewLifecycleOwner) { top250movies ->
            initTop250Movies(top250movies)
        }
    }

    private fun initInTheatersMovies(inTheatersMovies: List<InTheatersComingSoonMovie>) {
        val viewPagerAdapter = InTheatersMoviesAdapter(inTheatersMovies)

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(24))
        compositePageTransformer.addTransformer { page, position ->
            val transform = 1 - abs(position)
            page.scaleY = (0.85f + transform * 0.15f)
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

    private fun initPopularMovies(popularMovies: List<PopularMovieShow>) {
        binding.recyclerviewPopular.apply {
            adapter = PopularMoviesAdapter(popularMovies)
        }
    }

    private fun initTop250Movies(top250Movies: List<Top250MovieShow>) {
        binding.recyclerviewTop250.apply {
            adapter = Top250MoviesAdapter(top250Movies)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}