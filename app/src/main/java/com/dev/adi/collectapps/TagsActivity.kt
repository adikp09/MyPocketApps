package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tags.*

class TagsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tags)

    }

    fun calculate (text : String) {
        var count = editText15.text.matches("#".toRegex())
        var textCount = "#".toRegex()
    }
}
