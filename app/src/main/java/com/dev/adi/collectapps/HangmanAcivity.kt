package com.dev.adi.collectapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast

class HangmanAcivity : AppCompatActivity() {
    var current_game = CurrentGameStatus()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hangman_acivity)
        val wordView = findViewById<View>(R.id.word) as TextView
        val scoreText = findViewById<View>(R.id.score) as TextView

        System.out.println("Game Created with Word: " + current_game._raw_word)
        System.out.println("Game Created with Score: " + current_game._score)
        System.out.println("Game Created with Score: " + current_game._display_word)

        wordView.text = current_game._display_word
        scoreText.text = Integer.toString(current_game._score)

    }


    fun new_guess(view: View) {

        val wordView = findViewById<View>(R.id.word) as TextView
        val scoreText = findViewById<View>(R.id.score) as TextView
        val letter_guessed = findViewById<View>(R.id.new_letter) as TextView

        val new_guess = letter_guessed.text.toString()
        if (new_guess != "") {
            current_game.try_to_insert_letter(new_guess)

            wordView.text = current_game._display_word
            scoreText.text = Integer.toString(current_game._score)
            letter_guessed.text = ""

            if (current_game.word_completed()) {
                Toast.makeText(applicationContext, "Congrats!!!! \n You guessed " + current_game._raw_word.toUpperCase() + " correctly", Toast.LENGTH_LONG).show()
                reset(view)
            }
        }
    }


    fun reset(view: View) {
        val wordView = findViewById<View>(R.id.word) as TextView
        val scoreText = findViewById<View>(R.id.score) as TextView

        current_game = CurrentGameStatus()
        wordView.text = current_game._display_word
        scoreText.text = Integer.toString(current_game._score)
    }
}
