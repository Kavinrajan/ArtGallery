package com.kavin.artgallery.data.usecases

import com.kavin.artgallery.data.repository.ArtGalleryRepository
import com.kavin.artgallery.utils.AppConstants
import javax.inject.Inject

class SearchPhotosUsecase @Inject constructor(private val repository: ArtGalleryRepository) {
    suspend operator fun invoke(
        query: String,
        pageNum: Int = 1,
        pageSize: Int = AppConstants.API.PHOTOS_PER_PAGE
    ) = repository.searchPhotos(
        query = query,
        pageNumber = pageNum,
        pageSize = pageSize
    )
}