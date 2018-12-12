package com.dev.adi.collectapps.tweat

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import android.widget.Toast
import com.dev.adi.collectapps.R
import com.dev.adi.collectapps.tweat.adapter.HomeAdapter
import com.dev.adi.collectapps.tweat.model.DataHome
import com.dev.adi.collectapps.tweat.model.DataSpecies
import com.dev.adi.collectapps.tweat.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_home_feeds.*
import kotlinx.android.synthetic.main.content_home_feeds.*
import java.util.*




class HomeFeedsActivity : AppCompatActivity(), TweatView {
    private lateinit var homePresenter : HomePresenter
    private lateinit var homeAdapter: HomeAdapter
    var myPrefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_feeds)
        setSupportActionBar(toolbar)
        myPrefs = getSharedPreferences("tweeat1", Context.MODE_PRIVATE);

        homePresenter = HomePresenter(this)
        homePresenter.getSpecies()
        homePresenter.getHomeFeeds()

        homeAdapter = HomeAdapter(this)
        rv_feeds.layoutManager = LinearLayoutManager(this)
        rv_feeds.itemAnimator = DefaultItemAnimator()
        rv_feeds.adapter = homeAdapter

        fab.setOnClickListener {
            startActivity(Intent(this, TweatActivity::class.java))
        }
    }

    override fun successGetHome(list: ArrayList<DataHome>) {
        homeAdapter.addData(list)
        homeAdapter.notifyDataSetChanged()
    }

    override fun emptyFeed(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun failResponse(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun successGetSpecies(list: ArrayList<DataSpecies>) {
        homeAdapter.addSpecies(list)
        homeAdapter.notifyDataSetChanged()
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("Choose an item")
        val arrayAdapter = ArrayAdapter<DataSpecies>(this, android.R.layout.select_dialog_item, homePresenter.dataSpecies)

        mBuilder.setSingleChoiceItems(arrayAdapter, 0) { dialog, which ->
            homePresenter.idSpecies = which + 1
            val editor = myPrefs?.edit()
            if (editor != null) {
                editor.putInt("speciesId", homePresenter.idSpecies)
                editor.apply()
                editor.commit()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//
//        menuInflater.inflate(R.menu.dashboard, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        return if (id == R.id.action_account) {
//            true
//        } else super.onOptionsItemSelected(item)
//    }
}
