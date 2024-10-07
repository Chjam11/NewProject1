package com.example.wordlegame

object FourLetterWordList {

    // A long string of four-letter words separated by commas
    val fourLetterWords = "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,..."

    // Returns a list of four-letter words
    fun getAllFourLetterWords(): List<String> {
        return fourLetterWords.split(",")
    }

    // Returns a random four-letter word from the list in uppercase
    fun getRandomFourLetterWord(): String {
        val allWords = getAllFourLetterWords()
        return allWords.random().uppercase()
    }
}
