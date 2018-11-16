package com.dev.adi.collectapps.sorting

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import com.dev.adi.collectapps.R
import com.dev.adi.collectapps.sorting.helper.EndlessOnScrollListener
import com.dev.adi.collectapps.sorting.model.CommentModel
import kotlinx.android.synthetic.main.activity_sorting.*


class SortingActivity : AppCompatActivity(), MainView {

    lateinit var presenter: SortingPresenter
    internal lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorting)
        setSupportActionBar(mToolbar)

        presenter = SortingPresenter()
        presenter.onAttach(this)

        adapter = CommentAdapter(arrayListOf())
        rv_coment.layoutManager = LinearLayoutManager(this@SortingActivity)
        rv_coment.itemAnimator = DefaultItemAnimator()
        rv_coment.adapter = adapter
        rv_coment.addOnScrollListener(scrollData())

        BuilderDialog(this).createDialog(button18, presenter)

        presenter.requestData()

//        et_search.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                presenter.searchBody(et_search.text.toString().toLowerCase())
//                return@setOnEditorActionListener true
//            }
//            return@setOnEditorActionListener false
//        }
    }

    private fun scrollData(): EndlessOnScrollListener {
        return object : EndlessOnScrollListener() {
            override fun onLoadMore(page: Int) {
                presenter.loadMore(page)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.dashboard, menu)
        val mSearch = menu.findItem(R.id.action_search)
        val mSearchView = mSearch.actionView as SearchView
        mSearchView.queryHint = "Search"
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.searchBody(newText.toLowerCase())
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun resetData(commentModel: MutableList<CommentModel>) {
        adapter.clear()
        adapter.updateAll(commentModel)
    }

    override fun successGetData() {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT)
    }

    override fun updateData(commentModel: MutableList<CommentModel>) {
        adapter.updateAll(commentModel)
    }

    override fun error() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
    }

    override fun onAttachView() {
    }

    override fun onDettachView() {
        presenter.stopTask()
    }

    override fun dataNotFound() {
        Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show()
    }
}
