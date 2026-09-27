package com.kavin.artgallery

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.adobe.marketing.mobile.Analytics
import com.adobe.marketing.mobile.Identity
import com.adobe.marketing.mobile.Lifecycle
import com.adobe.marketing.mobile.MobileCore
import com.kavin.artgallery.utils.isNight
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ArtGalleryApp: Application() {
    override fun onCreate() {
        super.onCreate()
        setupDayNightMode()
        MobileCore.setApplication(this);
        try {
            Analytics.registerExtension()
           // MobileServices.registerExtension() //Register Mobile Services with Mobile Core
            Lifecycle.registerExtension()
            Identity.registerExtension()
            MobileCore.start(null)
        } catch (e: Exception) {
            //Log the exception
        }

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

