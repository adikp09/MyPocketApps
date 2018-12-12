package com.dev.adi.collectapps.tweat

import com.dev.adi.collectapps.tweat.model.DataHome
import com.dev.adi.collectapps.tweat.model.DataSpecies

interface TweatView {
    //home interface
    fun successGetHome(list: ArrayList<DataHome>)
    fun emptyFeed(text : String)
    fun failResponse(text : String)

    //species interface
    fun successGetSpecies(list: ArrayList<DataSpecies>)
}