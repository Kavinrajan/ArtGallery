package com.kavin.artgallery.ai

import com.kavin.artgallery.domain.Artwork
import com.kavin.artgallery.domain.ArtworkAiTags
import com.kavin.artgallery.domain.ArtworkImage
import com.kavin.artgallery.domain.ArtworkSearchDocument
import com.kavin.artgallery.domain.AiGalleryUiState
import com.kavin.artgallery.domain.AskGalleryAssistantUseCase
import com.kavin.artgallery.domain.FindSimilarArtworksUseCase
import com.kavin.artgallery.domain.GenerateArtworkDescriptionUseCase
import com.kavin.artgallery.domain.GenerateArtworkTagsUseCase
import com.kavin.artgallery.domain.ai.AiAvailability
import com.kavin.artgallery.domain.ai.AiAvailabilityChecker
import com.kavin.artgallery.domain.ai.AiImageDescriber
import com.kavin.artgallery.domain.ai.AiTextGenerator
import com.kavin.artgallery.domain.ai.ArtworkEmbeddingGenerator
import kotlin.math.abs

class NoOpAiTextGenerator : AiTextGenerator {
    override suspend fun generate(prompt: String): Result<String> {
        return Result.success(
            if (prompt.contains("offline art gallery", ignoreCase = true)) {
                "The gallery does not contain enough information to answer that confidently."
            } else {
                "AI assistance is unavailable on this device. The gallery remains fully usable without it."
            }
        )
    }
}

class NoOpAiImageDescriber : AiImageDescriber {
    override suspend fun describe(image: ArtworkImage): Result<String> {
        val fallback = image.description ?: image.altText ?: "This artwork is a visual composition with layered shapes and texture."
        return Result.success(fallback)
    }
}

class NoOpArtworkEmbeddingGenerator : ArtworkEmbeddingGenerator {
    override suspend fun generateEmbedding(text: String): Result<FloatArray> {
        val values = FloatArray(8)
        text.lowercase().forEachIndexed { index, char ->
            val position = index % values.size
            values[position] += (char.code.toFloat() / 100f)
        }
        return Result.success(values)
    }
}

class NoOpAiAvailabilityChecker : AiAvailabilityChecker {
    override suspend fun check(): AiAvailability {
        return AiAvailability.NotSupported
    }
}

class DefaultAskGalleryAssistantUseCase(
    private val aiTextGenerator: AiTextGenerator
) : AskGalleryAssistantUseCase {
    override suspend fun invoke(question: String): Result<String> {
        if (question.isBlank()) {
            return Result.failure(IllegalArgumentException("Question cannot be blank."))
        }
        return aiTextGenerator.generate(question)
            .recoverCatching { "AI features are currently unavailable. Try again later." }
    }
}

class DefaultGenerateArtworkDescriptionUseCase(
    private val aiTextGenerator: AiTextGenerator
) : GenerateArtworkDescriptionUseCase {
    override suspend fun invoke(artwork: Artwork): Result<String> {
        val title = artwork.description ?: artwork.user?.name ?: "Artwork"
        val prompt = "Describe the artwork in neutral language. Title: $title. " +
            "Description: ${artwork.description ?: "No description provided"}. " +
            "Colors: ${artwork.color ?: "unknown"}."
        return aiTextGenerator.generate(prompt)
            .recoverCatching { "Artwork description is unavailable offline." }
    }
}

class DefaultGenerateArtworkTagsUseCase(
    private val aiTextGenerator: AiTextGenerator
) : GenerateArtworkTagsUseCase {
    override suspend fun invoke(artwork: Artwork): Result<ArtworkAiTags> {
        val title = artwork.description ?: "Untitled"
        val color = artwork.color ?: "neutral"
        val tags = listOfNotNull(
            title.takeIf { it.isNotBlank() },
            "artwork",
            color,
            "gallery"
        ).distinct().map { it.trim() }.filter { it.isNotEmpty() }

        return Result.success(
            ArtworkAiTags(
                tags = tags,
                colors = listOf(color),
                mood = "neutral",
                style = "contemporary"
            )
        )
    }
}

class DefaultFindSimilarArtworksUseCase : FindSimilarArtworksUseCase {
    override suspend fun invoke(artworkId: String, limit: Int): Result<List<Artwork>> {
        if (artworkId.isBlank()) return Result.failure(IllegalArgumentException("Artwork id cannot be blank."))
        return Result.success(emptyList())
    }
}

fun sanitizeAiText(value: String?): String {
    val cleaned = value ?: return ""
    return cleaned.replace("\u0000", "")
        .replace(Regex("(?i)(ignore previous instructions|system prompt|override.*instructions)"), "")
        .trim()
}

fun sanitizeAiTags(tags: List<String>): List<String> = tags.map { sanitizeAiText(it) }
    .filter { it.isNotEmpty() }
    .distinct()

fun sanitizeAiColorList(colors: List<String>): List<String> = colors.map { it.trim().lowercase() }
    .filter { it.isNotEmpty() }
    .distinct()

fun isSafeAiOutput(output: String): Boolean {
    val lower = output.lowercase()
    val blocked = listOf(
        "rm ", "chmod", "exec(", "http://", "https://", "adb shell", "sqlite3", "drop table",
        "startactivity", "am start", "file://", "content://"
    )
    return blocked.none { lower.contains(it) }
}

fun buildArtworkSearchText(artwork: Artwork): String {
    val title = artwork.description ?: artwork.user?.name ?: "Artwork"
    val tags = artwork.tags?.joinToString(", ") ?: ""
    return listOfNotNull(
        "Title: $title",
        "Description: ${artwork.description ?: ""}",
        "Photographer: ${artwork.user?.name ?: ""}",
        "Tags: $tags",
        "Style: ${artwork.color ?: ""}",
        "Colors: ${artwork.color ?: ""}",
        "Mood: neutral"
    ).joinToString("\n")
}
