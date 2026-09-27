package com.kavin.artgallery.domain.ai

import com.kavin.artgallery.domain.ArtworkImage

interface AiTextGenerator {
    suspend fun generate(prompt: String): Result<String>
}

interface AiImageDescriber {
    suspend fun describe(image: ArtworkImage): Result<String>
}

interface ArtworkEmbeddingGenerator {
    suspend fun generateEmbedding(text: String): Result<FloatArray>
}

interface AiAvailabilityChecker {
    suspend fun check(): AiAvailability
}
