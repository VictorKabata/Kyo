package com.vickikbt.kyoskinterview.ui.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.vickikbt.kyoskinterview.R
import com.vickikbt.kyoskinterview.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        initUI()

    }

    private fun initUI() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}