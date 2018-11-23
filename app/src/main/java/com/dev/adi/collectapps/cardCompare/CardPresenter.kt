package com.dev.adi.collectapps.cardCompare

import android.util.Log.e
import com.dev.adi.collectapps.cardCompare.model.CardModels
import com.dev.adi.collectapps.cardCompare.model.RespondCard
import com.dev.adi.collectapps.cardCompare.model.RespondCompare
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardPresenter (private val viewCard: ViewCard) {

    private var id1 = -1
    private var id2 = -1
    private val cardSize = 13
    var list = arrayListOf<CardModels>()

    fun setCard(listData : ArrayList<CardModels>) {
        list.addAll(listData)
    }

    fun int() {
        for (i in 0 until cardSize) {
            list.add(CardModels(i + 1 , i))
        }
        viewCard.successPlay(list)
    }

    fun tempCompare(id : Int) {
        when {
            id1 == -1 -> {
                id1 = id
                viewCard.showCompare("$id1 -")
            }
            id2 == -1 -> {
                id2 = id
                viewCard.showCompare("$id1 - $id2")
                reqCompareCard(id1, id2)
            }
        }
    }

    fun processCompare(id1 : Int, id2 : Int, result: Int) {
        val item1 = list.find { it.id == result }
        val item2 = list.find { it.id == if(result == id1) id2 else id1 }

        item1?.let {
            list[list.indexOf(item2)] = it
        }

        item2?.let {
            list[list.indexOf(item1)] = it
        }

        this.id1 = -1
        this.id2 = -1
        viewCard.resultCompare(id1, id2, result)
        viewCard.successCompare(list)
    }

    fun autoSorting() {
        var i = 0
        var j: Int
        while (i < list.size) {
            j = 1
            while (j < list.size - i) {
                reqCompareCard(list[j-1].id, list[j].id)
                j++
            }
            i++
        }
    }

    fun reqPlayCard() {
        val postServices = DataRepository.create()
        postServices.getCardPlay().enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Toast.makeText(context,"\"${t.message}\"", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response?.let {
                    if (it.isSuccessful) {
                        it.body()?.let {
                            int()
                        }
                    } else {
//                        Toast.makeText(context,"Fail", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    fun reqCompareCard(id1 : Int, id2 : Int) {
        val postServices = DataRepository.create()
        postServices.getCardCompare(id1, id2).enqueue(object : Callback<RespondCompare> {
            override fun onFailure(call: Call<RespondCompare>, t: Throwable) {
//                Toast.makeText(context,"\"${t.message}\"", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<RespondCompare>, response: Response<RespondCompare>) {
                response?.let {
                    if (it.isSuccessful) {
                        it.body()?.let {
                            processCompare(id1, id2, it.greater)
                        }
                    } else {
//                        Toast.makeText(context,"Fail", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    fun reqShowCard() {
        val postServices = DataRepository.create()
        postServices.getCardShow().enqueue(object : Callback<ArrayList<RespondCard>> {
            override fun onFailure(call: Call<ArrayList<RespondCard>>, t: Throwable) {
//                Toast.makeText(context,"\"${t.message}\"", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ArrayList<RespondCard>>, response: Response<ArrayList<RespondCard>>) {
                response?.let {
                    if (it.isSuccessful) {
                        it.body()?.let {
                            e("show", Gson().toJson(it))
                            viewCard.successGetAllCard(it)
                        }
                    } else {
//                        Toast.makeText(context,"Fail", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
