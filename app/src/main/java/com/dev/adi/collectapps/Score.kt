package com.dev.adi.collectapps

class Score internal constructor() {
    fun update_score_by(delta: Int) {
        score += delta
    }

    fun update_score_correct_letter() {
        score += 10
    }

    fun update_score_incorrect_letter() {
        score -= 10
    }

    fun return_score(): Int {
        return score
    }

    init {
        score = 1000
    }

    companion object {
        internal var score = 100
    }
}
