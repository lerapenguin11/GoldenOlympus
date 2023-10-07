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

class SevenFragment : Fragment() {
    private var _binding : FragmentSevenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSevenBinding.inflate(inflater, container, false)

        getStory(3000L, MenuFragment())

        return binding.root
    }
}