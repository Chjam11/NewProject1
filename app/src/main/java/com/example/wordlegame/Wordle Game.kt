package com.example.wordlegame

import android.os.Parcel
import android.os.Parcelable

class WordleGame(private val targetWord: String) : Parcelable {
    var attempts = 0
    val maxAttempts = 6
    val wordLength = targetWord.length

    constructor(parcel: Parcel) : this(parcel.readString() ?: "") {
        attempts = parcel.readInt()
    }

    // Checks if the guessed word length is valid
    fun isValidWord(word: String): Boolean {
        return word.length == wordLength
    }

    // Processes the user's guess and returns feedback
    fun makeGuess(guess: String): String {
        if (guess.length != wordLength) {
            return "Invalid Guess Length"
        }

        attempts++
        val result = StringBuilder()

        for (i in guess.indices) {
            val guessedChar = guess[i]
            val targetChar = targetWord[i]

            when {
                guessedChar == targetChar -> result.append("O") // Correct letter and position
                targetWord.contains(guessedChar) -> result.append('*') // Correct letter but wrong position
                else -> result.append('X') // Incorrect letter
            }
        }
        return result.toString()
    }

    // Checks if the game is over (all attempts used)
    fun isGameOver(): Boolean {
        return attempts >= maxAttempts
    }

    // Checks if the word has been guessed correctly
    fun isWordGuessed(guess: String): Boolean {
        return guess.equals(targetWord, ignoreCase = true)
    }

    // Parcelable implementation
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(targetWord)
        parcel.writeInt(attempts)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WordleGame> {
        override fun createFromParcel(parcel: Parcel): WordleGame {
            return WordleGame(parcel)
        }

        override fun newArray(size: Int): Array<WordleGame?> {
            return arrayOfNulls(size)
        }
    }
}
