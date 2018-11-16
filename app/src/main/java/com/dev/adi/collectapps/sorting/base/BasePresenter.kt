package com.dev.adi.collectapps.sorting.base

interface BasePresenter <in T : BaseView> {

    fun onAttach(view : T)
    fun onDettach()
}