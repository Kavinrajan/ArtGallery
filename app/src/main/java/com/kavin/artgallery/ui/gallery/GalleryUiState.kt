package com.kavin.artgallery.ui.gallery

sealed class GalleryUiState

object LoadingState: GalleryUiState()
object LoadingNextPageState: GalleryUiState()
object ContentState : GalleryUiState()
object ContentNextPageState : GalleryUiState()
object EmptyState : GalleryUiState()
class ErrorState(val message: String): GalleryUiState()
class ErrorNextPageState(val message: String): GalleryUiState()
