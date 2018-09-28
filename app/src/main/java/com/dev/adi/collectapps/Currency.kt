package com.dev.adi.collectapps

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_currency.*
import org.json.JSONObject
import java.net.URL

class Currency : AppCompatActivity() {

    lateinit var progressDialog: ProgressDialog

    lateinit var jsonObject : JSONObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        progressDialog = ProgressDialog(this)
        getCurrency().execute()
    }

    inner class getCurrency : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {
            return URL("https://api.exchangeratesapi.io/latest?base=USD").readText()
        }

        override fun onPostExecute(result: String?) {
            progressDialog.hide()
            super.onPostExecute(result)
            jsonObject = JSONObject(result)
            val dollar = jsonObject.getJSONObject("rates")
            Log.e("lelele", dollar.toString())
            textView4.text = dollar.toString()
        }
    }
}
