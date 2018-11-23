package com.dev.adi.collectapps.cardCompare

import com.dev.adi.collectapps.cardCompare.model.CardModels
import com.dev.adi.collectapps.cardCompare.model.RespondCard

interface ViewCard {
    fun successPlay(cardModels: List<CardModels>)
    fun resultCompare(cardId1 : Int, cardId2: Int, result : Int)
    fun successGetAllCard (cardModels: ArrayList<RespondCard>)
    fun showCompare(text : String)
    fun successCompare(list : MutableList<CardModels>)
}