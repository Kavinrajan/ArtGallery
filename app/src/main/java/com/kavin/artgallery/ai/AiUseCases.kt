package com.kavin.artgallery.domain

interface GenerateArtworkDescriptionUseCase {
    suspend operator fun invoke(artwork: Artwork): Result<String>
}

interface GenerateArtworkTagsUseCase {
    suspend operator fun invoke(artwork: Artwork): Result<ArtworkAiTags>
}

interface SearchArtworkUseCase {
    suspend operator fun invoke(query: String, limit: Int = 10): Result<List<Artwork>>
}

interface SemanticArtworkSearchUseCase {
    suspend operator fun invoke(query: String, limit: Int = 10): Result<List<Artwork>>
}

interface AskGalleryAssistantUseCase {
    suspend operator fun invoke(question: String): Result<String>
}

interface FindSimilarArtworksUseCase {
    suspend operator fun invoke(artworkId: String, limit: Int = 5): Result<List<Artwork>>
}

data class RagContext(
    val artworks: List<Artwork>
)

data class RagAnswer(
    val answer: String,
    val sourceArtworkIds: List<String>
)

interface GalleryAgent {
    suspend fun handle(request: AgentRequest): AgentResponse
}

data class AgentRequest(
    val requestId: String,
    val message: String,
    val context: Map<String, String> = emptyMap()
)

data class AgentResponse(
    val requestId: String,
    val message: String,
    val artifacts: List<String> = emptyList()
)
