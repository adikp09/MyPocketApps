package com.dev.adi.collectapps.tweat.presenter

import android.util.Log.e
import com.dev.adi.collectapps.tweat.controler.DataRepository
import com.dev.adi.collectapps.tweat.model.ResponseTweat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweatPresenter (val view : TweatView) {
    fun tweat(speciesId : Int, targetId : Int) {
        val service = DataRepository.create()
        service.tweat(speciesId).enqueue(object : Callback<ResponseTweat> {
            override fun onFailure(call: Call<ResponseTweat>, t: Throwable) {
                t.message?.let { view.failedTweat(it) }
            }

            override fun onResponse(call: Call<ResponseTweat>, response: Response<ResponseTweat>) {
                e("respon", response.toString())
                response?.let {
                    it.body()?.let {
                        view.successTweat(it.success)
                    }
                }
            }

        })
    }
}

interface TweatView {
    fun successTweat(text :Boolean)
    fun failedTweat(text: String)
}
