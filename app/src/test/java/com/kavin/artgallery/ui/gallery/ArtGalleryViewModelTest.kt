package com.com.kavin.artgallery.ui.gallery

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.MockTestUtil
import com.com.kavin.artgallery.MainCoroutinesRule
import com.kavin.artgallery.data.DataState
import com.kavin.artgallery.data.usecases.GetPopularPhotosUsecase
import com.kavin.artgallery.data.usecases.SearchPhotosUsecase
import com.kavin.artgallery.model.PhotoModel
import com.kavin.artgallery.ui.gallery.ContentState
import com.kavin.artgallery.ui.gallery.GalleryUiState
import com.kavin.artgallery.ui.gallery.GalleryViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GalleryViewModelTest {

    // Subject under test
    private lateinit var viewModel: GalleryViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @MockK
    lateinit var getPopularPhotosUsecase: GetPopularPhotosUsecase

    @MockK
    lateinit var searchPhotosUsecase: SearchPhotosUsecase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test when GalleryViewModel is initialized, popular photos are fetched`() = runBlocking {
        // Given
        val givenPhotos = MockTestUtil.createPhotos(3)
        val uiObserver = mockk<Observer<GalleryUiState>>(relaxed = true)
        val photosListObserver = mockk<Observer<List<PhotoModel>>>(relaxed = true)

        // When
        coEvery { getPopularPhotosUsecase.invoke(any(), any(), any()) }
            .returns(flowOf(DataState.success(givenPhotos)))

        // Invoke
        viewModel = GalleryViewModel(getPopularPhotosUsecase, searchPhotosUsecase)
        viewModel.uiStateLiveData.observeForever(uiObserver)
        viewModel.photosListLiveData.observeForever(photosListObserver)

        // Then
        coVerify(exactly = 1) { getPopularPhotosUsecase.invoke() }
        verify { uiObserver.onChanged(match { it == ContentState }) }
        verify { photosListObserver.onChanged(match { it.size == givenPhotos.size }) }

    }

}