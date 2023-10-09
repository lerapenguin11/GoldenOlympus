package com.example.goldenolympus.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goldenolympus.R
import com.example.goldenolympus.databinding.FragmentFifthBinding
import com.example.goldenolympus.databinding.FragmentMenuBinding
import com.example.goldenolympus.presentation.story.SixthFragment
import com.example.goldenolympus.utilits.getStory
import com.example.goldenolympus.utilits.replaceFragmentMain

class MenuFragment : Fragment() {
    private var _binding : FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.icMenu1.setOnClickListener { replaceFragmentMain(PoseidonFragment()) }

        return binding.root
    }
}