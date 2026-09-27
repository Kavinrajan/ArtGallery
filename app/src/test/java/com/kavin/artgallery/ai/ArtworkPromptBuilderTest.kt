package com.kavin.artgallery.ai

import com.kavin.artgallery.ai.prompt.ArtworkPromptBuilder
import org.junit.Assert.assertTrue
import org.junit.Test

class ArtworkPromptBuilderTest {

    private val builder = ArtworkPromptBuilder()

    @Test
    fun `description prompt includes safety requirements and metadata`() {
        val prompt = builder.buildDescriptionPrompt(
            title = "Coastal Morning",
            photographer = "A. Smith",
            context = "Muted tones and ocean view"
        )

        assertTrue(prompt.contains("Maximum 60 words"))
        assertTrue(prompt.contains("Coastal Morning"))
        assertTrue(prompt.contains("A. Smith"))
        assertTrue(prompt.contains("Muted tones and ocean view"))
    }

    @Test
    fun `rag prompt includes gallery context and user question`() {
        val prompt = builder.buildRagAnswerPrompt(
            context = "Title: Mountain Lake",
            query = "Show peaceful blue landscapes"
        )

        assertTrue(prompt.contains("Answer ONLY using the supplied gallery context"))
        assertTrue(prompt.contains("Title: Mountain Lake"))
        assertTrue(prompt.contains("Show peaceful blue landscapes"))
    }
}
