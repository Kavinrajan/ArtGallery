package com.kavin.artgallery.ai

import com.kavin.artgallery.domain.ArtworkSearchDocument
import com.kavin.artgallery.domain.SemanticSearchResult
import com.kavin.artgallery.domain.VectorSearchEngine
import com.kavin.artgallery.domain.cosineSimilarity

class VectorSearchEngineImpl : VectorSearchEngine {
    override fun search(
        queryVector: FloatArray,
        documents: List<ArtworkSearchDocument>,
        limit: Int
    ): List<SemanticSearchResult> {
        if (documents.isEmpty()) return emptyList()

        return documents.map { document ->
            val score = cosineSimilarity(queryVector, document.embedding)
            SemanticSearchResult(document.artworkId, score)
        }
            .filter { it.score.isFinite() }
            .sortedByDescending { it.score }
            .take(limit.coerceAtLeast(0))
    }
}
