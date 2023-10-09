package com.example.goldenolympus.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goldenolympus.R
import com.example.goldenolympus.databinding.FragmentInfoBinding
import com.example.goldenolympus.databinding.FragmentMenuBinding
import com.example.goldenolympus.utilits.replaceFragmentMain

class InfoFragment : Fragment() {
    private var _binding : FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInfoBinding.inflate(inflater, container, false)

        binding.icGotIt.setOnClickListener { replaceFragmentMain(MenuFragment()) }

        return binding.root
    }
}