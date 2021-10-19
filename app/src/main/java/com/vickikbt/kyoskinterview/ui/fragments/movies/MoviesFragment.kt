package com.vickikbt.kyoskinterview.ui.fragments.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.vickikbt.domain.models.InTheatersMovie
import com.vickikbt.domain.models.PopularMovie
import com.vickikbt.domain.models.Top250Movie
import com.vickikbt.kyoskinterview.R
import com.vickikbt.kyoskinterview.databinding.FragmentMoviesBinding
import com.vickikbt.kyoskinterview.ui.adapters.PopularMoviesAdapter
import com.vickikbt.kyoskinterview.ui.adapters.Top250MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        viewModel.inTheatersMovies.observe(viewLifecycleOwner){
            
        }

        viewModel.popularMovies.observe(viewLifecycleOwner) { popularMovies ->
            initPopularMovies(popularMovies)
        }

        viewModel.top250Movies.observe(viewLifecycleOwner) { top250movies ->
            initTop250Movies(top250movies)
        }
    }

    private fun initInTheatersMovies(inTheatersMovies:List<InTheatersMovie>) {

    }

    private fun initPopularMovies(popularMovies: List<PopularMovie>) {
        binding.recyclerviewPopular.apply {
            adapter = PopularMoviesAdapter(popularMovies)
        }
    }

    private fun initTop250Movies(top250Movies: List<Top250Movie>) {
        binding.recyclerviewTop250.apply {
            adapter = Top250MoviesAdapter(top250Movies)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}