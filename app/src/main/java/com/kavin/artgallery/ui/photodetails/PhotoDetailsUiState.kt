package com.kavin.artgallery.ui.photodetails

sealed class PhotoDetailsUiState

object LoadingState: PhotoDetailsUiState()
object ContentState: PhotoDetailsUiState()
class ErrorState(val message: String): PhotoDetailsUiState()