package com.example.goldenolympus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goldenolympus.databinding.ActivityMainBinding
import com.example.goldenolympus.presentation.story.FirstFragment
import com.example.goldenolympus.utilits.APP_ACTIVITY
import com.example.goldenolympus.utilits.replaceFragmentMain
import com.example.goldenolympus.utilits.setStatusBarGradiantMain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        APP_ACTIVITY = this
        setContentView(binding.root)
        setStatusBarGradiantMain(this)

        replaceFragmentMain(FirstFragment())
    }
}