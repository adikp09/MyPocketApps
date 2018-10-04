package com.dev.adi.collectapps

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener {
            val listItems = arrayOf(
                    "Meetup.com",
                    "Multi Calendar",
                    "Multi Calculator",
                    "Minesweeper",
                    "Antre Bank",
                    "Weather App",
                    "CalendarActivity",
                    getString(R.string.calculator),
                    getString(R.string.bmi),
                    getString(R.string.temperature),
                    getString(R.string.age),
                    getString(R.string.TicTacToe),
                    getString(R.string.currency),
                    getString(R.string.hangman),
                    getString(R.string.todo),
                    getString(R.string.cashier),
                    getString(R.string.numeric),
                    getString(R.string.wtf),
                    getString(R.string.scrabble),
                    getString(R.string.snakeko),
                    getString(R.string.battleship),
                    "New Todo"
                    )
            val mBuilder = AlertDialog.Builder(this@MainActivity)
            mBuilder.setTitle("Choose an item")
            mBuilder.setItems(listItems
            ) { _, which ->
                when {
                    listItems[which] == "Meetup.com" ->
                        startActivity(Intent(this, MeetupActivity::class.java))
                    listItems[which] == "Multi Calendar" ->
                        startActivity(Intent(this, MultiCalendarActivity::class.java))
                    listItems[which] == "MultiCalculator" ->
                        startActivity(Intent(this, MultiCalculator::class.java))
                    listItems[which] == "Minesweeper" ->
                        startActivity(Intent(this, MinesweeperActivity::class.java))
                    listItems[which] == "Antre Bank" ->
                        startActivity(Intent(this, AntreActivity::class.java))
                    listItems[which] == "CalendarActivity" ->
                        startActivity(Intent(this, CalendarActivity::class.java))
                    listItems[which] == "Weather App" ->
                        startActivity(Intent(this, WeatherApp::class.java))
                    listItems[which] == getString(R.string.calculator) ->
                        startActivity(Intent(this, CalculatorActivity::class.java))
                    listItems[which] == getString(R.string.bmi) ->
                        startActivity(Intent(this, BmiActivity::class.java))
                    listItems[which] == getString(R.string.temperature) ->
                        startActivity(Intent(this, TemperatureConversion::class.java))
                    listItems[which] == getString(R.string.age) ->
                        startActivity(Intent(this, CalculateAge::class.java))
                    listItems[which] == getString(R.string.TicTacToe) ->
                        startActivity(Intent(this, TicTacTok::class.java))
                    listItems[which] == getString(R.string.currency) ->
                        startActivity(Intent(this, Currency::class.java))
                    listItems[which] == getString(R.string.hangman) ->
                        startActivity(Intent(this, HangmanAcivity::class.java))
                    listItems[which] == getString(R.string.todo) ->
                        startActivity(Intent(this, AddTodo::class.java))
                    listItems[which] == getString(R.string.cashier) ->
                        startActivity(Intent(this, CashierActivity::class.java))
                    listItems[which] == getString(R.string.numeric) ->
                        startActivity(Intent(this, NumericActivity::class.java))
                    listItems[which] == getString(R.string.wtf) ->
                        startActivity(Intent(this, WtfActivity::class.java))
                    listItems[which] == getString(R.string.scrabble) ->
                        startActivity(Intent(this, ScrabbleActivity::class.java))
                    listItems[which] == getString(R.string.snakeko) ->
                        startActivity(Intent(this, Snake::class.java))
                    listItems[which] == getString(R.string.battleship) ->
                        startActivity(Intent(this, Battleship::class.java))
                    listItems[which] == "New Todo" ->
                        startActivity(Intent(this, TodoMvc::class.java))
                }
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }
    }
}
