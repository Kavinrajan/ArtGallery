package com.kavin.artgallery.ai

import com.kavin.artgallery.domain.ArtworkRepository
import com.kavin.artgallery.domain.Artwork
import com.kavin.artgallery.domain.ArtworkSearchDocument
import com.kavin.artgallery.domain.RagAnswer
import com.kavin.artgallery.domain.RagContext
import com.kavin.artgallery.domain.ai.AiTextGenerator
import com.kavin.artgallery.domain.ai.ArtworkEmbeddingGenerator
import com.kavin.artgallery.domain.VectorSearchEngine
import com.kavin.artgallery.domain.SemanticSearchResult
import com.kavin.artgallery.ai.prompt.ArtworkPromptBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OnDeviceRagEngine(
    private val embeddingGenerator: ArtworkEmbeddingGenerator,
    private val vectorSearchEngine: VectorSearchEngine,
    private val artworkRepository: ArtworkRepository,
    private val aiTextGenerator: AiTextGenerator,
    private val promptBuilder: ArtworkPromptBuilder = ArtworkPromptBuilder()
) {
    suspend fun answerQuestion(question: String): Result<RagAnswer> = withContext(Dispatchers.IO) {
        runCatching {
            val artworks = artworkRepository.getAllArtworks()
            if (artworks.isEmpty()) {
                return@runCatching RagAnswer(
                    answer = "The gallery does not contain enough information.",
                    sourceArtworkIds = emptyList()
                )
            }

            val queryVector = embeddingGenerator.generateEmbedding(question)
                .getOrElse { throw it }

            val documents = artworks.map { artwork ->
                val text = buildSearchableText(artwork)
                val embedding = embeddingGenerator.generateEmbedding(text).getOrElse { throw it }
                ArtworkSearchDocument(
                    artworkId = artwork.id ?: "unknown",
                    searchableText = text,
                    embedding = embedding
                )
            }

            val matches = vectorSearchEngine.search(queryVector, documents, limit = 5)
            val matchedArtworks = artworks.filter { artwork ->
                matches.any { it.artworkId == (artwork.id ?: "") }
            }

            val context = buildContext(matchedArtworks)
            val prompt = promptBuilder.buildRagAnswerPrompt(context, question)
            val answerText = aiTextGenerator.generate(prompt).getOrElse { throw it }

            RagAnswer(
                answer = answerText,
                sourceArtworkIds = matchedArtworks.mapNotNull { it.id }
            )
        }
    }

    private fun buildContext(artworks: List<Artwork>): String {
        return artworks.joinToString(separator = "\n\n") { artwork ->
            listOfNotNull(
                "Title: ${artwork.description ?: "Unknown"}",
                "Description: ${artwork.description ?: "No description"}",
                "Photographer: ${artwork.user?.name ?: "Unknown"}",
                "Tags: ${artwork.tags?.joinToString(", ") ?: ""}",
                "Color: ${artwork.color ?: "unknown"}"
            ).joinToString("\n")
        }
    }

    private fun buildSearchableText(artwork: Artwork): String {
        return buildList {
            add("Title: ${artwork.description ?: ""}")
            add("Description: ${artwork.description ?: ""}")
            add("Photographer: ${artwork.user?.username ?: artwork.user?.name ?: ""}")
            add("Tags: ${artwork.tags?.joinToString(", ") ?: ""}")
            add("Color: ${artwork.color ?: ""}")
        }.joinToString("\n")
    }
}
