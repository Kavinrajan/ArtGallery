package com.kavin.artgallery.domain.ai

sealed interface AiAvailability {
    data object Available : AiAvailability
    data object NotSupported : AiAvailability
    data object ModelNotReady : AiAvailability
    data class Error(val message: String) : AiAvailability
}
