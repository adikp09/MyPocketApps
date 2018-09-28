package com.dev.adi.collectapps

//2 blank tiles (scoring 0 points)
//1 point: A ×19, N ×9, E ×8, I ×8, T ×5, U ×5, R ×4, O ×3, S ×3,
//2 points: K ×3, M ×3,
//3 points: D ×4, G ×3,
//4 points: L ×3, H ×2, P ×2,
//5 points: B ×4, Y ×2, F ×1, W ×1,
//8 points: C ×3, V ×1,


object ScrabbleScore {
    private fun scoreLetter(letter: Char) = when (letter.toUpperCase()) {
        'A', 'N', 'E', 'I', 'T', 'U', 'R', '0', 'S' -> 1
        'K', 'M' -> 2
        'D', 'G' -> 3
        'L', 'H', 'P' -> 2
        'B', 'Y', 'F', 'W' -> 1
        'C', 'V' -> 1
        else -> 2
    }

    fun scoreWord(word: String): Int = word.sumBy(::scoreLetter)
}