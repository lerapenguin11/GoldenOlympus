package com.example.goldenolympus.presentation.story

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goldenolympus.R
import com.example.goldenolympus.databinding.FragmentFifthBinding
import com.example.goldenolympus.databinding.FragmentFourthBinding
import com.example.goldenolympus.utilits.getStory

class FifthFragment : Fragment() {
    private var _binding : FragmentFifthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFifthBinding.inflate(inflater, container, false)

        getStory(5000L, SixthFragment())

        return binding.root
    }
}