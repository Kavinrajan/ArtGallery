/*
* Copyright 2021 Wajahat Karim (https://wajahatkarim.com)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.kavin.artgallery.ui.photodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import coil.request.ImageRequest
import com.kavin.artgallery.base.BaseFragment
import com.kavin.artgallery.databinding.PhotoDetailsFragmentBinding
import com.kavin.artgallery.model.PhotoModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailsFragment : BaseFragment<PhotoDetailsFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> PhotoDetailsFragmentBinding
        get() = PhotoDetailsFragmentBinding::inflate

    private val viewModel: PhotoDetailsViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val photo = arguments?.getParcelable<PhotoModel>("photo")
        if (photo == null) {
            findNavController().popBackStack()
            return
        }

        setupViews()
        initObservations()

        viewModel.initPhotoModel(photo)
    }

    private fun setupViews() {
        bi.imageLoader.visibility = View.VISIBLE
        bi.photoView.alpha = 0f
    }

    private fun initObservations() {
        viewModel.photoModelLiveData.observe(viewLifecycleOwner) { photo ->
            bi.photoView.load(photo.urls?.full) {
                crossfade(true)
                crossfade(200)
                placeholder(android.R.color.transparent)
                error(android.R.drawable.ic_menu_report_image)
                listener(
                    onStart = {
                        bi.imageLoader.visibility = View.VISIBLE
                        bi.photoView.alpha = 0f
                    },
                    onSuccess = { _, _ ->
                        bi.imageLoader.visibility = View.GONE
                        bi.photoView.animate().alpha(1f).setDuration(250).start()
                    },
                    onError = { _, _ ->
                        bi.imageLoader.visibility = View.GONE
                        bi.photoView.alpha = 1f
                    }
                )
            }
        }
    }
}
