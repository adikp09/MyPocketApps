package com.dev.adi.collectapps.sorting.helper

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EndlessOnScrollListenerTest {

    @Mock
    private lateinit var mockRecyclerView : RecyclerView

    @Mock
    private lateinit var mockLayoutManager : LinearLayoutManager

    @Test
    fun testOnScrolled() {
        var endlessObj = object : EndlessOnScrollListener() {
            override fun onLoadMore(page: Int) {
                assertEquals("call with 0",1, page)
            }
        }

        `when`(mockRecyclerView.layoutManager).thenReturn(mockLayoutManager)
        `when`(mockRecyclerView.childCount).thenReturn(2)
        `when`(mockLayoutManager.itemCount).thenReturn(2)
        `when`(mockLayoutManager.findFirstVisibleItemPosition()).thenReturn(0)
        endlessObj.onScrolled(mockRecyclerView, 0, 0)
    }
}