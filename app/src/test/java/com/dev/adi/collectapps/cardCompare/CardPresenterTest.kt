package com.dev.adi.collectapps.cardCompare

import com.dev.adi.collectapps.cardCompare.model.CardModels
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
open class CardPresenterTest internal constructor() {

    @Mock
    lateinit var mockViewCard: ViewCard
    lateinit var  presenter : CardPresenter

    @Before
    fun setUp() {
        presenter = Mockito.spy((CardPresenter(mockViewCard)))

        var stockCard = arrayListOf<CardModels>()
        var stockDummy = arrayListOf<CardModels>()
        stockDummy.add(CardModels(2, 1))
        stockDummy.add(CardModels(1, 2))
        stockDummy.add(CardModels(3, 3))
        stockDummy.add(CardModels(4, 4))
        stockDummy.add(CardModels(5, 5))
        stockDummy.add(CardModels(6, 6))
        stockDummy.add(CardModels(8, 7))
        stockDummy.add(CardModels(10, 8))
        stockDummy.add(CardModels(11, 9))
        stockDummy.add(CardModels(13, 10))
        stockDummy.add(CardModels(12, 11))
        stockDummy.add(CardModels(7, 12))
        stockDummy.add(CardModels(9, 13))
        stockCard.addAll(stockDummy)
        presenter.setCard(stockCard)
    }

    @Test
    fun initTest() {
        Mockito.`when`(presenter.reqCompareCard(Mockito.anyInt(), Mockito.anyInt())).thenAnswer {
            val id1 = it.getArgument<Int>(0)
            val id2 = it.getArgument<Int>(0)
            doDummy(id1, id2)
        }
        presenter.autoSorting()

    }

    fun doDummy(id1 : Int, id2 : Int) {
        var resultDummy = arrayListOf<String>()
        resultDummy.add("A")
        resultDummy.add("2")
        resultDummy.add("3")
        resultDummy.add("4")
        resultDummy.add("5")
        resultDummy.add("6")
        resultDummy.add("7")
        resultDummy.add("8")
        resultDummy.add("9")
        resultDummy.add("10")
        resultDummy.add("J")
        resultDummy.add("Q")
        resultDummy.add("K")

        val item1 = presenter.list.find { it.id == id1 }?.posisi?: ""
        val item2 = presenter.list.find { it.id == id2 }?.posisi?: ""
        if (resultDummy.indexOf(item1) < resultDummy.indexOf(item2)) {
            presenter.processCompare(id1, id2, id2)
        } else {
            presenter.processCompare(id1, id2, id1)
        }
    }
}