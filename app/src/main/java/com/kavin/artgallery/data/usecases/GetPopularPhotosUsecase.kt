package com.kavin.artgallery.data.usecases

import com.kavin.artgallery.data.repository.ArtGalleryRepository
import com.kavin.artgallery.utils.AppConstants
import javax.inject.Inject

/*
* A use-case to load the popular photos from Unsplash API
* */

class GetPopularPhotosUsecase @Inject constructor(private val repository: ArtGalleryRepository) {
    suspend operator fun invoke(
        pageNum: Int = 1,
        pageSize: Int = AppConstants.API.PHOTOS_PER_PAGE,
        orderBy: String = "popular"
    ) = repository.loadPhotos(
        pageNumber = pageNum,
        pageSize = pageSize,
        orderBy = orderBy
    )
}