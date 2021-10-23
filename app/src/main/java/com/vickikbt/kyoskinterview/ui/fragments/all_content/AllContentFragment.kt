package com.vickikbt.kyoskinterview.ui.fragments.all_content

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.kyoskinterview.R
import com.vickikbt.kyoskinterview.databinding.FragmentAllContentBinding
import com.vickikbt.kyoskinterview.ui.adapters.MoviesShowsRecyclerviewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class AllContentFragment : Fragment(R.layout.fragment_all_content) {
    private var _binding: FragmentAllContentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AllContentViewModel by viewModel()
    private val args: AllContentFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAllContentBinding.bind(view)

        initUI()
    }

    private fun initUI() {
        binding.textViewTitle.setOnClickListener { findNavController().navigateUp() }

        binding.textViewTitle.text = args.category.replace("_", " ")

        viewModel.getMoviesShowsByCategory(args.category).observe(viewLifecycleOwner) {
            initGrid(it)
        }
    }

    private fun initGrid(moviesShows: List<MovieShow>) {
        if (!moviesShows.isNullOrEmpty()) {
            val adapter = MoviesShowsRecyclerviewAdapter(moviesShows) {
                navigateToDetails(it.id)
            }
            binding.recyclerviewMoreContent.adapter = adapter
        }
    }

    private fun navigateToDetails(id: String) {
        val action = AllContentFragmentDirections.allContentToDetails(id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}