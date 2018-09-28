package com.dev.adi.collectapps

import java.util.*

class WordToGuess internal constructor(word: String) {
    var current_state = ""
    var raw_text = ""
    init {
        raw_text = word
        for (i in 0 until raw_text.length)
            current_state = current_state + "_"
        println("Current State: $current_state")
        val r = Random()
        val i1 = r.nextInt(raw_text.length - 1)
        println("Length of raw_text: " + raw_text.length)
        println("Random Number: $i1")
        println("Random ScrabWord: " + raw_text.substring(i1, i1 + 1))
        insert_letter(raw_text.substring(i1, i1 + 1))
    }

    fun insert_letter(letter: String) {
        for (i in 0 until raw_text.length) {
            val c = raw_text.toLowerCase()[i]
            if (c == letter.toLowerCase().toCharArray()[0]) {
                current_state = current_state.substring(0, i) + c + current_state.substring(i + 1, current_state.length)
            }
        }
    }

    fun letter_belongs_to_word(letter: String): Boolean {
        val c: Char
        c = letter.toLowerCase().toCharArray()[0]

        for (i in 0 until raw_text.length) {
            val w = raw_text.toLowerCase()[i]
            if (w == c) {
                return true
            }
        }
        return false
    }

    fun letter_already_in_word(letter: String): Boolean {
        val c: Char
        c = letter.toLowerCase().toCharArray()[0]

        for (i in 0 until current_state.length) {
            val w = current_state.toLowerCase()[i]
            println("Current char: $c word char: $w")
            if (w == c) {
                return true
            }
        }
        return false
    }

    fun display_word(): String {
        var temp = ""
        // Append and pre append a space over
        for (i in 0 until current_state.length) {
            temp = temp + " " + current_state[i] + " "
        }
        // Remove the leading and trailing blank space
        temp = temp.substring(1, temp.length - 1)
        return temp
    }

    fun return_raw_word(): String {
        return raw_text
    }

    fun word_completed(): Boolean {
        return current_state == raw_text
    }
}