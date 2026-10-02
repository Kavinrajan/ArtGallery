package com.kavin.artgallery.ai

import com.kavin.artgallery.domain.ArtworkSearchDocument
import com.kavin.artgallery.domain.cosineSimilarity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class VectorSearchEngineTest {

    private val engine = VectorSearchEngineImpl()

    @Test
    fun `cosine similarity identical vectors`() {
        val a = floatArrayOf(1f, 2f, 3f)
        val b = floatArrayOf(1f, 2f, 3f)
        assertEquals(1f, cosineSimilarity(a, b), 0.0001f)
    }

    @Test
    fun `cosine similarity opposite vectors`() {
        val a = floatArrayOf(1f, 2f, 3f)
        val b = floatArrayOf(-1f, -2f, -3f)
        assertEquals(-1f, cosineSimilarity(a, b), 0.0001f)
    }

    @Test
    fun `cosine similarity zero vectors`() {
        val a = floatArrayOf(0f, 0f)
        val b = floatArrayOf(0f, 0f)
        assertEquals(0f, cosineSimilarity(a, b), 0.0001f)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `cosine similarity mismatched dimensions`() {
        cosineSimilarity(floatArrayOf(1f, 2f), floatArrayOf(1f))
    }

    @Test
    fun `vector search returns ranked results`() {
        val docs = listOf(
            ArtworkSearchDocument("a", "sunset", floatArrayOf(1f, 0f)),
            ArtworkSearchDocument("b", "mountain", floatArrayOf(0.2f, 0.8f)),
            ArtworkSearchDocument("c", "forest", floatArrayOf(0f, 1f))
        )
        val results = engine.search(floatArrayOf(1f, 0f), docs, 2)
        assertTrue(results.isNotEmpty())
        assertEquals("a", results.first().artworkId)
    }

    @Test
    fun `vector search empty document list`() {
        val results = engine.search(floatArrayOf(1f, 0f), emptyList(), 5)
        assertTrue(results.isEmpty())
    }
}
