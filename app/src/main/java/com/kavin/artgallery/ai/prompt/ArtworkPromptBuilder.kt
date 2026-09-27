package com.kavin.artgallery.ai.prompt

class ArtworkPromptBuilder {

    fun buildDescriptionPrompt(title: String?, photographer: String?, context: String?): String {
        return """
            You are an assistant for a digital art gallery.

            Analyze the supplied artwork.

            Requirements:
            - Maximum 60 words
            - Do not invent artist information
            - Do not invent historical facts
            - Describe visible characteristics
            - Mention dominant colors
            - Mention likely subject matter
            - Use neutral language

            Artwork title: ${title.orEmpty()}
            Photographer: ${photographer.orEmpty()}
            Additional context: ${context.orEmpty()}
        """.trimIndent()
    }

    fun buildTagsPrompt(title: String?, description: String?, photographer: String?): String {
        return """
            Extract concise visual tags for this artwork.

            Return only valid JSON with fields:
            tags, colors, mood, style.

            Rules:
            - Use plain English tags.
            - Use no URLs, no commands, no executable content.
            - Base output strictly on visible content.

            Title: ${title.orEmpty()}
            Description: ${description.orEmpty()}
            Photographer: ${photographer.orEmpty()}
        """.trimIndent()
    }

    fun buildStyleClassificationPrompt(title: String?, description: String?): String {
        return """
            Classify the visual style of the artwork.

            Use one short style label and explain briefly.
            Do not invent artist styles or historical movements.

            Title: ${title.orEmpty()}
            Description: ${description.orEmpty()}
        """.trimIndent()
    }

    fun buildColorExtractionPrompt(title: String?, description: String?): String {
        return """
            Identify the dominant colors visible in the artwork.
            Return a short list of colors, no more than 5.

            Title: ${title.orEmpty()}
            Description: ${description.orEmpty()}
        """.trimIndent()
    }

    fun buildMoodExtractionPrompt(title: String?, description: String?): String {
        return """
            Determine the likely mood of this artwork.
            Use one short mood word or a brief phrase.

            Title: ${title.orEmpty()}
            Description: ${description.orEmpty()}
        """.trimIndent()
    }

    fun buildSemanticSearchQueryPrompt(query: String): String {
        return """
            Convert the user's request into a concise search query for an art gallery.
            Keep it to a few keywords that describe subjects, colors, mood, or style.

            User query: ${query}
        """.trimIndent()
    }

    fun buildRagAnswerPrompt(context: String, query: String): String {
        return """
            You are an assistant for an offline art gallery.

            Answer ONLY using the supplied gallery context.

            If the context does not contain enough information,
            say that the gallery does not contain enough information.

            Do not invent artwork details.

            Gallery context:
            $context

            User question:
            $query
        """.trimIndent()
    }
}
