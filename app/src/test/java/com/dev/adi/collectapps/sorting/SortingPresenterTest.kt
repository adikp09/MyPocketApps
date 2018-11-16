package com.dev.adi.collectapps.sorting

import com.dev.adi.collectapps.sorting.model.CommentModel
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SortingPresenterTest {

    @Mock
    private lateinit var mockSortingView: MainView

    @Test
    fun loadMore() {
        val sortingPresenter = SortingPresenter()

        var dataComment : ArrayList<CommentModel> = arrayListOf()
        dataComment.add(CommentModel("ini body 1", "ini email 1", 1, "nama 1", 1))
        dataComment.add(CommentModel("ini body 2", "ini email 2", 2, "nama 2", 2))
        sortingPresenter.setTempDataComment(dataComment)

        sortingPresenter.onAttach(mockSortingView)
        try {
            sortingPresenter.loadMore(1)
        } catch (e : IllegalArgumentException) {
            fail("found IllegalArgumentException, loadMore should not trigger exception")
        }
    }
}