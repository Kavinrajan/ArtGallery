package com.kavin.artgallery.di.module

import com.kavin.artgallery.ai.DefaultAskGalleryAssistantUseCase
import com.kavin.artgallery.ai.DefaultFindSimilarArtworksUseCase
import com.kavin.artgallery.ai.DefaultGenerateArtworkDescriptionUseCase
import com.kavin.artgallery.ai.DefaultGenerateArtworkTagsUseCase
import com.kavin.artgallery.ai.NoOpAiAvailabilityChecker
import com.kavin.artgallery.ai.NoOpAiImageDescriber
import com.kavin.artgallery.ai.NoOpAiTextGenerator
import com.kavin.artgallery.ai.NoOpArtworkEmbeddingGenerator
import com.kavin.artgallery.domain.AskGalleryAssistantUseCase
import com.kavin.artgallery.domain.FindSimilarArtworksUseCase
import com.kavin.artgallery.domain.GenerateArtworkDescriptionUseCase
import com.kavin.artgallery.domain.GenerateArtworkTagsUseCase
import com.kavin.artgallery.domain.ai.AiAvailabilityChecker
import com.kavin.artgallery.domain.ai.AiImageDescriber
import com.kavin.artgallery.domain.ai.AiTextGenerator
import com.kavin.artgallery.domain.ai.ArtworkEmbeddingGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AiModule {

    @Provides
    @Singleton
    fun provideAiTextGenerator(): AiTextGenerator = NoOpAiTextGenerator()

    @Provides
    @Singleton
    fun provideAiImageDescriber(): AiImageDescriber = NoOpAiImageDescriber()

    @Provides
    @Singleton
    fun provideArtworkEmbeddingGenerator(): ArtworkEmbeddingGenerator = NoOpArtworkEmbeddingGenerator()

    @Provides
    @Singleton
    fun provideAiAvailabilityChecker(): AiAvailabilityChecker = NoOpAiAvailabilityChecker()

    @Provides
    @Singleton
    fun provideAskGalleryAssistantUseCase(
        generator: AiTextGenerator
    ): AskGalleryAssistantUseCase = DefaultAskGalleryAssistantUseCase(generator)

    @Provides
    @Singleton
    fun provideGenerateArtworkDescriptionUseCase(
        generator: AiTextGenerator
    ): GenerateArtworkDescriptionUseCase = DefaultGenerateArtworkDescriptionUseCase(generator)

    @Provides
    @Singleton
    fun provideGenerateArtworkTagsUseCase(
        generator: AiTextGenerator
    ): GenerateArtworkTagsUseCase = DefaultGenerateArtworkTagsUseCase(generator)

    @Provides
    @Singleton
    fun provideFindSimilarArtworksUseCase(): FindSimilarArtworksUseCase = DefaultFindSimilarArtworksUseCase()
}
