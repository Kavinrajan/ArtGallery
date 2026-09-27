package com.kavin.artgallery.domain

sealed interface AiGalleryUiState {
    data object Idle : AiGalleryUiState
    data object Loading : AiGalleryUiState
    data class Success(
        val answer: String,
        val artworkIds: List<String>
    ) : AiGalleryUiState

    data class Error(
        val message: String
    ) : AiGalleryUiState
}
