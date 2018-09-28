package com.dev.adi.collectapps

import java.util.*

class Scrabbles(private val word: String) {

    private val score: Any? = null

    fun getScore(): Int {
        return getScore(word)
    }

    companion object {

        private val letterScores = HashMap<Char, Int>()

        init {
            letterScores['a'] = 1
            letterScores['e'] = 1
            letterScores['i'] = 1
            letterScores['o'] = 1
            letterScores['u'] = 1
            letterScores['l'] = 1
            letterScores['n'] = 1
            letterScores['r'] = 1
            letterScores['s'] = 1
            letterScores['t'] = 1
            letterScores['d'] = 2
            letterScores['g'] = 2
            letterScores['b'] = 3
            letterScores['c'] = 3
            letterScores['m'] = 3
            letterScores['p'] = 3
            letterScores['f'] = 4
            letterScores['h'] = 4
            letterScores['v'] = 4
            letterScores['w'] = 4
            letterScores['y'] = 4
            letterScores['k'] = 5
            letterScores['j'] = 8
            letterScores['x'] = 8
            letterScores['q'] = 10
            letterScores['z'] = 10
        }

        fun getScore(input: String?): Int {
            if (input == null || input.trim { it <= ' ' }.isEmpty()) {
                return 0
            }

            var score = 0

            for (letter in input.toLowerCase().toCharArray()) {
                score += letterScores[letter].toString().toInt()
            }

            return score
        }
    }
}
