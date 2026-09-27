package com.kavin.artgallery.integration

import com.kavin.artgallery.domain.AgentRequest
import com.kavin.artgallery.domain.AgentResponse
import com.kavin.artgallery.domain.GalleryAgent

class GallerySearchAgent : GalleryAgent {
    override suspend fun handle(request: AgentRequest): AgentResponse {
        val normalized = request.message.lowercase()
        val keywords = listOf("mountain", "peaceful", "blue", "sunset", "abstract", "landscape")
        val found = keywords.filter { normalized.contains(it) }
        return AgentResponse(
            requestId = request.requestId,
            message = if (found.isEmpty()) {
                "Find relevant artworks matching the request."
            } else {
                "Find artwork matching: ${found.joinToString(", ")}."
            },
            artifacts = found
        )
    }
}

class RecommendationAgent : GalleryAgent {
    override suspend fun handle(request: AgentRequest): AgentResponse {
        return AgentResponse(
            requestId = request.requestId,
            message = "Recommend similar or complementary artworks based on the user request.",
            artifacts = listOf("similar-artworks")
        )
    }
}
