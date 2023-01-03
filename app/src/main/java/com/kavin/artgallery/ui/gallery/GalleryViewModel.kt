package com.kavin.artgallery.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kavin.artgallery.data.DataState
import com.kavin.artgallery.data.usecases.GetPopularPhotosUsecase
import com.kavin.artgallery.data.usecases.SearchPhotosUsecase
import com.kavin.artgallery.model.PhotoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getPopularPhotosUsecase: GetPopularPhotosUsecase,
    private val searchPhotosUsecase: SearchPhotosUsecase
) : ViewModel() {
    private var _uiState = MutableLiveData<GalleryUiState>()
    var uiStateLiveData: LiveData<GalleryUiState> = _uiState

    private var _photosList = MutableLiveData<List<PhotoModel>>()
    var photosListLiveData: LiveData<List<PhotoModel>> = _photosList

    private var pageNumber = 1
    private var searchQuery: String = ""

    init {
        fetchPhotos(pageNumber)
    }

    fun loadMorePhotos() {
        pageNumber++
        if(searchQuery == "")
            fetchPhotos(pageNumber)
        else {
            searchPhotos(searchQuery, pageNumber)
        }
    }

    fun retry() {
        if (searchQuery == "")
            fetchPhotos(pageNumber)
        else
            searchPhotos(searchQuery, pageNumber)
    }

    fun searchPhotos(query: String) {
        searchQuery = query
        pageNumber = 1
        searchPhotos(query, pageNumber)
    }

    fun fetchPhotos(page: Int) {
        _uiState.postValue(if (page == 1) LoadingState else LoadingNextPageState)
        viewModelScope.launch {
            getPopularPhotosUsecase(page).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        if (page == 1) {
                            // First page
                            _uiState.postValue(ContentState)
                            _photosList.postValue(dataState.data)
                        } else {
                            // Any other page
                            _uiState.postValue(ContentNextPageState)
                            var currentList = arrayListOf<PhotoModel>()
                            _photosList.value?.let { currentList.addAll(it) }
                            currentList.addAll(dataState.data)
                            _photosList.postValue(currentList)
                        }
                    }

                    is DataState.Error -> {
                        if (page == 1) {
                            _uiState.postValue(ErrorState(dataState.message))
                        } else {
                            _uiState.postValue(ErrorNextPageState(dataState.message))
                        }
                    }
                }
            }
        }
    }

    fun solution(A: IntArray?): Int {

        // check corner cases
        if (A == null || A.size == 0) {
            return 1
        }

        // Each element of array A is an integer within the range [−1,000,000..1,000,000].
        // We are going to keep track only of positive numbers we have visited
        val visitedPositives = BooleanArray(1000000 + 1) // initialized by the JVM with false

        // traverse all target array and keep track of positive integers
        run {
            var i = 0
            val c = A.size
            while (i < c) {
                // get current number
                val current = A[i]

                // keep track of visited positive numbers
                if (current > 0) {
                    visitedPositives[current] = true
                }
                ++i
            }
        }

        // traverse visited positive numbers array and keep the index > 0 of the first position marked as false
        var i = 1
        val c = visitedPositives.size
        while (i < c) {
            if (!visitedPositives[i]) {
                return i
            }
            i++
        }

        // fallback: all positive numbers exist in the A array
        return 10000001
    }

    private fun searchPhotos(query: String, page: Int) {
        _uiState.postValue( if(page == 1) LoadingState else LoadingNextPageState)
        viewModelScope.launch {
            searchPhotosUsecase(query, page).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        if(page == 1) {
                            // first page
                            _uiState.postValue(ContentState)
                            _photosList.postValue(dataState.data)
                        } else {
                            // other pages
                            _uiState.postValue(ContentNextPageState)
                            var currentList = arrayListOf<PhotoModel>()
                            _photosList.value?.let {
                                currentList.addAll(dataState.data)
                                _photosList.postValue(currentList)
                            }
                        }
                    }
                    is DataState.Error -> {
                        if(page == 1 ) {
                            _uiState.postValue(ErrorState(dataState.message))
                        }  else {
                            _uiState.postValue(ErrorNextPageState(dataState.message))
                        }
                    }
                }

            }
        }
    }
}