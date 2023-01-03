package com.kavin.artgallery.data.repository

import com.kavin.artgallery.data.DataState
import com.kavin.artgallery.model.PhotoModel
import kotlinx.coroutines.flow.Flow

/*
* ArtGalleryRepository is an interface data layer to handle communication with any data source such as Server or local database.
* @see [ArtGalleryRepositoryImpl] for implementation of this class to utilize Unsplash API.
* */
interface ArtGalleryRepository {
    suspend fun loadPhotos(pageNumber: Int, pageSize: Int, orderBy: String): Flow<DataState<List<PhotoModel>>>
    suspend fun searchPhotos(query: String, pageNumber: Int, pageSize: Int): Flow<DataState<List<PhotoModel>>>
}