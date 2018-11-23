package com.dev.adi.collectapps.flipCoin

import android.view.Window
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CoinFlipActivityTest {

    @Mock
    lateinit var coinView: CoinView

    @Mock
    lateinit var mockWindow: Window

    @Test
    fun setUp() {
        val activity = spy(CoinFlipActivity())
        `when`(activity.window).thenReturn(mockWindow)
        activity.successGetData(CoinModel(false, 7, "head"))
//        Assert.asserztEquals("result",
//                CoinModel(false, 7, "head"),
//                CoinModel(false, 7, "head")
//        )
    }
}