package com.kavin.artgallery.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kavin.artgallery.domain.AiGalleryUiState
import com.kavin.artgallery.domain.AskGalleryAssistantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiGalleryViewModel @Inject constructor(
    private val askGalleryAssistantUseCase: AskGalleryAssistantUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AiGalleryUiState>(AiGalleryUiState.Idle)
    val uiState: StateFlow<AiGalleryUiState> = _uiState

    fun ask(question: String) {
        if (question.isBlank()) {
            _uiState.value = AiGalleryUiState.Error("Please enter a question.")
            return
        }

        _uiState.value = AiGalleryUiState.Loading
        viewModelScope.launch {
            val result = askGalleryAssistantUseCase(question)
            result.fold(
                onSuccess = { answer ->
                    _uiState.value = AiGalleryUiState.Success(answer, emptyList())
                },
                onFailure = { throwable ->
                    _uiState.value = AiGalleryUiState.Error(throwable.message ?: "AI assistant failed.")
                }
            )
        }
    }
}
