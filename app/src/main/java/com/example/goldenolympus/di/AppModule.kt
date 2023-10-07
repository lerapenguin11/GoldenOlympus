package com.example.goldenolympus.di

import android.app.Application
import com.example.goldenolympus.viewmodel.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<SettingsViewModel> {
        val appContext = androidContext().applicationContext

        SettingsViewModel(
            application = get()
        )
    }
}