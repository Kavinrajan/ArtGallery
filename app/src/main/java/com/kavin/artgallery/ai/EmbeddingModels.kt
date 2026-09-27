package com.kavin.artgallery.domain

import java.lang.Math.sqrt

data class ArtworkAiMetadata(
    val artworkId: String,
    val title: String? = null,
    val description: String? = null,
    val artist: String? = null,
    val tags: List<String> = emptyList(),
    val style: String? = null,
    val colors: List<String> = emptyList(),
    val mood: String? = null,
    val embedding: List<Float> = emptyList()
)

data class ArtworkAiTags(
    val tags: List<String>,
    val colors: List<String>,
    val mood: String?,
    val style: String?
)

data class ArtworkSearchDocument(
    val artworkId: String,
    val searchableText: String,
    val embedding: FloatArray
)

data class SemanticSearchResult(
    val artworkId: String,
    val score: Float
)

interface VectorSearchEngine {
    fun search(
        queryVector: FloatArray,
        documents: List<ArtworkSearchDocument>,
        limit: Int
    ): List<SemanticSearchResult>
}

fun cosineSimilarity(a: FloatArray, b: FloatArray): Float {
    require(a.size == b.size) { "Vector dimensions must match" }

    var dot = 0f
    var normA = 0f
    var normB = 0f

    for (i in a.indices) {
        dot += a[i] * b[i]
        normA += a[i] * a[i]
        normB += b[i] * b[i]
    }

    if (normA == 0f || normB == 0f) {
        return 0f
    }

    return dot / (sqrt(normA.toDouble()) * sqrt(normB.toDouble())).toFloat()
}
