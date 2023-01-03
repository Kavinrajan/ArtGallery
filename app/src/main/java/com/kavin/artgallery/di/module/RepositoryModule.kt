package com.kavin.artgallery.di.module

import android.app.Application
import com.kavin.artgallery.data.remote.UnsplashApiService
import com.kavin.artgallery.data.repository.ArtGalleryRepository
import com.kavin.artgallery.data.repository.ArtGalleryRepositoryImpl
import com.kavin.artgallery.utils.StringUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideStringUtils(app: Application): StringUtils {
        return StringUtils(app)
    }

    @Singleton
    @Provides
    fun provideArtGalleryRepository(stringUtils: StringUtils, apiService: UnsplashApiService): ArtGalleryRepository {
        return ArtGalleryRepositoryImpl(stringUtils, apiService)
    }

}
