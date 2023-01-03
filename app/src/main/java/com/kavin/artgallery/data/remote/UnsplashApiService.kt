package com.kavin.artgallery.data.remote

import com.kavin.artgallery.data.response.SearchPhotosResponse
import com.kavin.artgallery.model.PhotoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("photos")
    suspend fun loadPhotos(@Query("page") page: Int = 1,
                           @Query("per_page") numOfPhotos: Int = 10,
                           @Query("order_by") qorderBy: String = "popular") : ApiResponse<List<PhotoModel>>

    @GET("search/photos")
    suspend fun searchPhotos(@Query("query") query: String,
                           @Query("page") page: Int = 1,
                           @Query("per_page") numOfPhotos: Int = 10) : ApiResponse<SearchPhotosResponse>

    companion object {
        const val BASE_API_URL = "https://api.unsplash.com/"
    }

}