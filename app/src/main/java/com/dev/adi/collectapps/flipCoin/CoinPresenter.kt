package com.dev.adi.collectapps.flipCoin

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinPresenter (val coinView: CoinView, val context: Context) {
    fun init () {
        getTossPresenter()
    }

    private fun getTossPresenter () {
        val postServices = DataRepository.create()
        postServices.getToss().enqueue(object : Callback<CoinModel> {
            override fun onFailure(call: Call<CoinModel>, t: Throwable) {
                if (t != null) {
                    Toast.makeText(context,"\"${t.message}\"", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call<CoinModel>, response: Response<CoinModel>) {
                if (response?.isSuccessful!!) {
                    response?.let {
                        it.body()?.let {
                            coinView.successGetData(it)
                        }
                    }
                } else {
                    coinView.failureGetData()
                }
            }

        })
    }
}

interface CoinView {
    fun successGetData(coinModel: CoinModel)
    fun failureGetData()
}