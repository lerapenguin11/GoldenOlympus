package com.example.goldenolympus.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goldenolympus.databinding.ActivityMainBinding
import com.example.goldenolympus.presentation.story.FirstFragment
import com.example.goldenolympus.presentation.ui.MenuFragment
import com.example.goldenolympus.utilits.APP_ACTIVITY
import com.example.goldenolympus.utilits.replaceFragmentMain
import com.example.goldenolympus.utilits.setStatusBarGradiantMain
import com.example.goldenolympus.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        APP_ACTIVITY = this
        setContentView(binding.root)
        setStatusBarGradiantMain(this)

        val code = viewModel.codeSher.getInt("code", 0)
        if (code == 2){
            replaceFragmentMain(MenuFragment())
        } else{
            replaceFragmentMain(FirstFragment())
        }
    }
}