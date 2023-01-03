package com.kavin.artgallery.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment()  {

    private var _bi: VB? = null
    protected val bi: VB get() = _bi!!

    abstract val bindingInflater:(LayoutInflater, ViewGroup?, Boolean) -> VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _bi = bindingInflater(inflater, container, false)
        return _bi!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _bi = null
    }

}