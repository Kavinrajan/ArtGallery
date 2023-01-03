package com.kavin.artgallery

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kavin.artgallery.utils.isNight
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ArtGalleryApp: Application() {
    override fun onCreate() {
        super.onCreate()
        setupDayNightMode()
    }
    fun setupDayNightMode() {
        val mode = if (isNight()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}

