package com.dev.adi.collectapps.tweat

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log.e
import android.widget.Toast
import com.dev.adi.collectapps.R
import com.dev.adi.collectapps.tweat.presenter.TweatPresenter
import com.dev.adi.collectapps.tweat.presenter.TweatView
import kotlinx.android.synthetic.main.activity_tweat.*





class TweatActivity : AppCompatActivity(), TweatView {

    lateinit var presenter : TweatPresenter
    var myPrefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweat)
        myPrefs = getSharedPreferences("tweeat1", Context.MODE_PRIVATE)

        presenter = TweatPresenter(this)
        button21.setOnClickListener {
            presenter.tweat(editText16.text.toString().toInt(), editText16.text.toString().toInt())
//            val id = myPrefs?.getInt("speciesId", 0)
//            e("id", id.toString())
//            id?.let { it1 -> presenter.tweat(it1, editText16.text.toString().toInt()) }
        }

    }

    override fun successTweat(status: Boolean) {
        e("status", status.toString())
        if(status) {
            finish()
        } else {
            val id = myPrefs?.getInt("speciesId", 0)
//            Toast.makeText(this, "$id Cannot eat ${editText16.text}", Toast.LENGTH_SHORT).show()
        }

    }

    override fun failedTweat(text: String) {
        Toast.makeText(this, "Something Worng", Toast.LENGTH_SHORT).show()
    }
}
