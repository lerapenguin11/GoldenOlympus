package com.example.goldenolympus.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goldenolympus.R
import com.example.goldenolympus.databinding.FragmentPoseidonBinding
import com.example.goldenolympus.databinding.FragmentWinBinding
import com.example.goldenolympus.utilits.replaceFragmentMain

class WinFragment : Fragment() {
    private var _binding : FragmentWinBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWinBinding.inflate(inflater, container, false)

        val displayCoin = arguments?.getInt("coin")
        binding.tvCountCoin.text = displayCoin.toString()

        val displayTime = arguments?.getString("time")
        binding.tvSec.text = displayTime

        binding.btMenu.setOnClickListener { replaceFragmentMain(MenuFragment()) }

        return binding.root
    }
}