package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_fsa.*

class FsaActivity : AppCompatActivity() {

    private val stationList = arrayListOf<Station>()
    private lateinit var currentStation : Station
    private val finishRoute = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fsa)

        stationList.add(Station(1,"City Mall Station", 1, 2, R.drawable.city)) //0
        stationList.add(Station(2, "West Station", 4, 5, R.drawable.west)) //1
        stationList.add(Station(3, "South Station", 0, 3, R.drawable.south)) //2
        stationList.add(Station(4, "Railington Station", 0, 4, R.drawable.railington)) //3
        stationList.add(Station(5, "East Station", 1, 3, R.drawable.east)) //4
        stationList.add(Station(6, "Central Station", 0, 6, R.drawable.central)) //5
        stationList.add(Station(7, "Mid Station", 7, 3, R.drawable.midway)) //6
        stationList.add(Station(8, "North Station", 5, 8, R.drawable.north)) //7
        stationList.add(Station(9, "Suburbopolis Station", 0, 0, R.drawable.suburb)) //8

        resetRoute()

        bt_train_a.setOnClickListener {
            currentStation = chooseRoute(currentStation, "A")
            tv_station.text = currentStation.stationName
            img_station.setBackgroundResource(currentStation.icon)
        }

        bt_train_b.setOnClickListener {
            currentStation = chooseRoute(currentStation, "B")
            tv_station.text = currentStation.stationName
            img_station.setBackgroundResource(currentStation.icon)
        }

        bt_reset.setOnClickListener {
            resetRoute()
            tx_title.text = "You are currently at:"
            line_playing.visibility = View.VISIBLE
        }

    }

    private fun chooseRoute (currentStation: Station, routeName : String) : Station {
        return when (routeName) {
            "A" -> {
                if (currentStation.id == finishRoute) {
                    tx_title.text = "Finally arrived on:"
                    line_playing.visibility = View.GONE
                }
                stationList[currentStation.routeA]
            }
            "B" -> {
                if (currentStation.id == finishRoute) {
                    tx_title.text = "Finally arrived on:"
                    line_playing.visibility = View.GONE
                }
                stationList[currentStation.routeB]
            }
            else -> {
                stationList[0]
            }
        }
    }

    private fun resetRoute () {
        currentStation = stationList[0]
        tv_station.text = currentStation.stationName
        img_station.setBackgroundResource(currentStation.icon)
    }
}

class Station (
        val id: Int,
        val stationName : String,
        val routeA : Int,
        val routeB : Int,
        val icon : Int
)
