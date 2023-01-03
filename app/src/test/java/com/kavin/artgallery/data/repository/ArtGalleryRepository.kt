package com.com.kavin.artgallery.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.MockTestUtil
import com.com.kavin.artgallery.MainCoroutinesRule
import com.com.kavin.artgallery.data.remote.api.ApiUtil.successCall
import com.kavin.artgallery.data.DataState
import com.kavin.artgallery.data.remote.ApiResponse
import com.kavin.artgallery.data.remote.UnsplashApiService
import com.kavin.artgallery.data.remote.message
import com.kavin.artgallery.data.repository.ArtGalleryRepositoryImpl
import com.kavin.artgallery.model.PhotoModel
import com.kavin.artgallery.utils.StringUtils
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class ArtGalleryRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Subject under test
    private lateinit var repository: ArtGalleryRepositoryImpl

    @MockK
    private lateinit var apiService: UnsplashApiService

    @MockK
    private lateinit var stringUtils: StringUtils

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test loadPhotos() gives list of photos`() = runBlocking {
        // Given
        repository = ArtGalleryRepositoryImpl(stringUtils, apiService)
        val givenPhotosList = MockTestUtil.createPhotos(3)
        val apiCall = successCall(givenPhotosList)

        // when
        coEvery {
            apiService.loadPhotos(any(), any(), any())
        }.returns(apiCall)

        // Invoke
        val apiResponseFlow = repository.loadPhotos(1, 1, "")

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())

        val photosListDataState = apiResponseFlow.first()
        MatcherAssert.assertThat(photosListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(photosListDataState, CoreMatchers.instanceOf(DataState.Success::class.java))

        val photosList = (photosListDataState as DataState.Success).data
        MatcherAssert.assertThat(photosList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(photosList.size, CoreMatchers.`is`(givenPhotosList.size))

        coVerify(exactly = 1) { apiService.loadPhotos(any(), any(), any()) }
        confirmVerified(apiService)
    }

    @Test
    fun `test loadPhotos() gives empty list of photos`() = runBlocking {
        // Given
        repository = ArtGalleryRepositoryImpl(stringUtils, apiService)
        val givenPhotosList = MockTestUtil.createPhotos(0)
        val apiCall = successCall(givenPhotosList)

        // when
        coEvery {
            apiService.loadPhotos(any(), any(), any())
        }.returns(apiCall)

        // Invoke
        val apiResponseFlow = repository.loadPhotos(1, 1, "")

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())

        val photosListDataState = apiResponseFlow.first()
        MatcherAssert.assertThat(photosListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(photosListDataState, CoreMatchers.instanceOf(DataState.Success::class.java))

        val photosList = (photosListDataState as DataState.Success).data
        MatcherAssert.assertThat(photosList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(photosList.size, CoreMatchers.`is`(givenPhotosList.size))

        coVerify(exactly = 1) { apiService.loadPhotos(any(), any(), any()) }
        confirmVerified(apiService)
    }

    @Test
    fun `test loadPhotos() throws exception`() = runBlocking {
        // Given
        repository = ArtGalleryRepositoryImpl(stringUtils, apiService)
        val givenMessage = "Error Message_Test"
        val exception = Exception(givenMessage)
        val apiResponse = ApiResponse.exception<List<PhotoModel>>(exception)

        // When
        coEvery { apiService.loadPhotos(any(), any(), any()) }
            .returns(apiResponse)
        coEvery { stringUtils.somethingWentWrong() }
            .returns(givenMessage)

        // Invoke
        val apiResponseFlow = repository.loadPhotos(1, 1, "")

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(apiResponseFlow.count(), CoreMatchers.`is`(1))

        val apiResponseDataState = apiResponseFlow.first()
        MatcherAssert.assertThat(apiResponseDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(apiResponseDataState, CoreMatchers.instanceOf(DataState.Error::class.java))

        val errorMessage = (apiResponseDataState as DataState.Error).message
        MatcherAssert.assertThat(errorMessage, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(errorMessage, CoreMatchers.equalTo(givenMessage))

        coVerify(atLeast = 1) { apiService.loadPhotos(any(), any(), any()) }
        confirmVerified(apiService)
    }

    @Test
    fun `test loadPhotos() gives network error`() = runBlocking {
        // Given
        repository = ArtGalleryRepositoryImpl(stringUtils, apiService)
        val givenMessage = "Test Error Message"
        val body = givenMessage.toResponseBody("text/html".toMediaTypeOrNull())
        val apiResponse = ApiResponse.error<List<PhotoModel>>(Response.error(500, body))

        // When
        coEvery { apiService.loadPhotos(any(), any(), any()) }
            .returns(apiResponse)
        coEvery { stringUtils.somethingWentWrong() }
            .returns(givenMessage)

        // Invoke
        val apiResponseFlow = repository.loadPhotos(1, 1, "")

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(apiResponseFlow.count(), CoreMatchers.`is`(1))

        val apiResponseDataState = apiResponseFlow.first()
        MatcherAssert.assertThat(apiResponseDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(apiResponseDataState, CoreMatchers.instanceOf(DataState.Error::class.java))

        val errorMessage = (apiResponseDataState as DataState.Error).message
        MatcherAssert.assertThat(errorMessage, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(errorMessage, CoreMatchers.equalTo(apiResponse.message()))

        coVerify(atLeast = 1) { apiService.loadPhotos(any(), any(), any()) }
        confirmVerified(apiService)
    }

    @Test
    fun `test searchPhotos() gives list of photos`() = runBlocking {
        // Given
        repository = ArtGalleryRepositoryImpl(stringUtils, apiService)
        val apiResponse = MockTestUtil.createSearchPhotosResponse()
        val apiCall = successCall(apiResponse)

        // When
        coEvery { apiService.searchPhotos(any(), any(), any()) }
            .returns(apiCall)

        // Invoke
        val apiResponseFlow = repository.searchPhotos("", 1, 1)

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())
        val photosListDataState = apiResponseFlow.first()
        MatcherAssert.assertThat(photosListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(photosListDataState, CoreMatchers.instanceOf(DataState.Success::class.java))

        val photosList = (photosListDataState as DataState.Success).data
        MatcherAssert.assertThat(photosList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(photosList.size, CoreMatchers.`is`(apiResponse.photosList.size))

        coVerify(exactly = 1) { apiService.searchPhotos(any(), any(), any()) }
        confirmVerified(apiService)
        }
}