package com.example.goldenolympus.presentation.story

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goldenolympus.R
import com.example.goldenolympus.databinding.FragmentSevenBinding
import com.example.goldenolympus.databinding.FragmentSixthBinding
import com.example.goldenolympus.presentation.ui.MenuFragment
import com.example.goldenolympus.utilits.getStory
import com.example.goldenolympus.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SevenFragment : Fragment() {
    private var _binding : FragmentSevenBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SettingsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSevenBinding.inflate(inflater, container, false)

        getStory(3000L, MenuFragment())

        viewModel.getCode(1)

        return binding.root
    }
}