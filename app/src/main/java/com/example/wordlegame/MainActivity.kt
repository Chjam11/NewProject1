package com.example.wordlegame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var wordleGame: WordleGame
    private lateinit var wordInput: EditText
    private lateinit var guessButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var attemptsTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get a random word from FourLetterWordList
        val randomWord = FourLetterWordList.getRandomFourLetterWord()

        // Initialize the game with the random word
        wordleGame = WordleGame(randomWord)

        // Set up views from the layout
        wordInput = findViewById(R.id.wordInput)
        guessButton = findViewById(R.id.guessButton)
        resultTextView = findViewById(R.id.resultTextView)
        attemptsTextView = findViewById(R.id.attemptsTextView)

        // Set up the guess button
        guessButton.setOnClickListener {
            val guessedWord = wordInput.text.toString().uppercase()

            // Check if the guess is valid
            if (wordleGame.isValidWord(guessedWord)) {
                val result = wordleGame.makeGuess(guessedWord)
                resultTextView.text = result

                // Update attempts
                attemptsTextView.text = "Attempts: ${wordleGame.attempts}/${wordleGame.maxAttempts}"

                // Check if the game is over or if the word is guessed
                if (wordleGame.isWordGuessed(guessedWord)) {
                    resultTextView.text = "Congratulations! You guessed the word!"
                    guessButton.isEnabled = false
                } else if (wordleGame.isGameOver()) {
                    resultTextView.text = "Game over! The word was: ${randomWord}"
                    guessButton.isEnabled = false
                }

            } else {
                resultTextView.text = "Invalid guess length, try again."
            }
        }
    }
}

