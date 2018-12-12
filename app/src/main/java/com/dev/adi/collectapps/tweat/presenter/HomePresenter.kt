package com.dev.adi.collectapps.tweat.presenter

import com.dev.adi.collectapps.tweat.TweatView
import com.dev.adi.collectapps.tweat.controler.DataRepository
import com.dev.adi.collectapps.tweat.model.DataSpecies
import com.dev.adi.collectapps.tweat.model.ResponseHome
import com.dev.adi.collectapps.tweat.model.ResponseSpecies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter (val viewTweat : TweatView) {

    var dataSpecies : ArrayList<DataSpecies> = arrayListOf()
    var idSpecies :Int = 0

    fun getHomeFeeds() {
        val service = DataRepository.create()
        service.getHome().enqueue(object : Callback<ResponseHome> {
            override fun onFailure(call: Call<ResponseHome>, t: Throwable) {
                t.message?.let { viewTweat.failResponse(it) }
            }

            override fun onResponse(call: Call<ResponseHome>, response: Response<ResponseHome>) {
                if (response?.isSuccessful!!) {
                    val data = response.body()
                    if (data != null) {
                        viewTweat.successGetHome(data?.data)
                    } else {
                        viewTweat.emptyFeed("Empty Feeds")
                    }
                }
            }

        })
    }

    fun getSpecies() {
        val service = DataRepository.create()
        service.getSpecies().enqueue(object : Callback<ResponseSpecies> {
            override fun onResponse(call: Call<ResponseSpecies>, response: Response<ResponseSpecies>) {
                response?.let {
                    it.body()?.let {
                        dataSpecies.addAll(it.data)
                        viewTweat.successGetSpecies(it.data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSpecies>, t: Throwable) {
                t.message?.let { viewTweat.failResponse(it) }
            }

        })
    }
}