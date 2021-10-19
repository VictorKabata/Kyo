package com.vickikbt.kyoskinterview.ui.fragments.tv_shows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vickikbt.kyoskinterview.R
import com.vickikbt.kyoskinterview.databinding.FragmentTvShowsBinding


class TvShowsFragment : Fragment(R.layout.fragment_tv_shows) {

    private var _binding:FragmentTvShowsBinding?=null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentTvShowsBinding.bind(view)

        initUI()
    }

    private fun initUI(){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}