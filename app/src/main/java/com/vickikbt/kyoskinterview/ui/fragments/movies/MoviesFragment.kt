package com.vickikbt.kyoskinterview.ui.fragments.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.kyoskinterview.R
import com.vickikbt.kyoskinterview.databinding.FragmentMoviesBinding
import com.vickikbt.kyoskinterview.ui.adapters.InTheatersMoviesAdapter
import com.vickikbt.kyoskinterview.ui.adapters.MoviesShowsAdapter
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

    private fun initInTheatersMovies(inTheatersMovies: List<MovieShow>) {
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

    private fun initPopularMovies(popularMovies: List<MovieShow>) {
        val adapter = MoviesShowsAdapter(popularMovies) {
            navigateToDetails(it.id)
        }

        binding.recyclerviewPopular.adapter = adapter
    }

    private fun initTop250Movies(movies: List<MovieShow>) {
        val adapter = MoviesShowsAdapter(movies) {
            navigateToDetails(it.id)
        }
        binding.recyclerviewTop250.adapter = adapter
    }

    private fun navigateToDetails(id:String){
        val action=MoviesFragmentDirections.moviesToDetails(id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}