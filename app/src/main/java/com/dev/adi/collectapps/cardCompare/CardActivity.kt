package com.dev.adi.collectapps.cardCompare

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout
import com.dev.adi.collectapps.R
import com.dev.adi.collectapps.cardCompare.model.CardModels
import com.dev.adi.collectapps.cardCompare.model.RespondCard
import kotlinx.android.synthetic.main.activity_card.*

class CardActivity : AppCompatActivity(), ViewCard {

    private lateinit var presenter: CardPresenter
    private lateinit var progressDialog : ProgressDialog

    override fun successPlay(cardModels: List<CardModels>) {
        renderCard(cardModels, line_parent)
        progressDialog.hide()
    }

    override fun successGetAllCard(cardModels: ArrayList<RespondCard>) {
        renderCard2(cardModels, line_card)
    }

    override fun resultCompare(cardId1: Int, cardId2: Int, result: Int) {
        textView53.text = "$result is the biggest"
    }

    override fun showCompare(text: String) {
        textView53.text = text
    }

    override fun successCompare(list: MutableList<CardModels>) {
        renderCard(list, line_parent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        progressDialog = ProgressDialog(this)
        presenter = CardPresenter(this)
        presenter.reqPlayCard()
        progressDialog.setTitle("Please Wait..")
        progressDialog.show()
        textView53.setOnClickListener {
//            presenter.reqShowCard()
            presenter.autoSorting()
        }
    }

    private fun renderCard(cardSize: List<CardModels>, lineParent : LinearLayout) {
        lineParent.removeAllViews()
        val lineFirst = LinearLayout(this)
        lineFirst.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        cardSize.forEach {
            val buttonCard = Button(this)
            buttonCard.layoutParams = LinearLayout.LayoutParams(100, 100)
            buttonCard.text = it.id.toString()
            buttonCard.tag = it.id
            buttonCard.textSize = 12F
            lineFirst.addView(buttonCard)
            buttonCard.setOnClickListener {
                presenter.tempCompare(it.tag.toString().toInt())
            }
        }
        lineParent?.addView(lineFirst)
    }

    private fun renderCard2(cardSize: ArrayList<RespondCard>, lineParent : LinearLayout) {
        val lineFirst = LinearLayout(this)
        lineFirst.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        cardSize.forEach {
            val buttonCard = Button(this)
            buttonCard.layoutParams = LinearLayout.LayoutParams(100, 100)
            buttonCard.text = it.value
            buttonCard.tag = it.value
            buttonCard.textSize = 12F
            lineFirst.addView(buttonCard)
        }
        lineParent?.addView(lineFirst)
    }
}
