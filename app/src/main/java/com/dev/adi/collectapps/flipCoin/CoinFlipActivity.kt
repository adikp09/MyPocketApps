package com.dev.adi.collectapps.flipCoin

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import com.dev.adi.collectapps.R
import kotlinx.android.synthetic.main.activity_coin_flip.*


class CoinFlipActivity : AppCompatActivity(), CoinView {

    private lateinit var progressDialog: ProgressDialog
    private lateinit var presenter: CoinPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_flip)
        progressDialog = ProgressDialog(this)

        presenter = CoinPresenter(this, this)
        btn.setOnClickListener {
            progressDialog.setTitle("Please Wait..")
            progressDialog.show()
            presenter.init()
        }
    }

    override fun successGetData(coinModel: CoinModel) {
        flipCoin(coinModel)
        progressDialog.hide()
    }

    override fun failureGetData() {
        Toast.makeText(this,"Server Error", Toast.LENGTH_SHORT).show()
    }

    private fun flipCoin(coinModel: CoinModel) {
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.duration = 1000
        fadeOut.fillAfter = true
        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                when {
                    coinModel.resultPic == "head" -> coin.setImageResource(R.drawable.heads)
                    coinModel.resultPic == "tail" -> coin.setImageResource(R.drawable.tails)
                    coinModel.resultPic == "side" -> coin.setImageResource(R.drawable.side)
                }
                textView50.text = coinModel.flipCount.toString()

                val fadeIn = AlphaAnimation(0f, 1f)
                fadeIn.interpolator = DecelerateInterpolator()
                fadeIn.duration = 3000
                fadeIn.fillAfter = true

                coin.startAnimation(fadeIn)
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        coin.startAnimation(fadeOut)
    }
}
