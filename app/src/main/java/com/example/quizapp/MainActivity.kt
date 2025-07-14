package com.example.quizapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var answerTextView: TextView

    private var correct = 0
    private var currentQuestionIndex = 0

    private val questionBank = arrayOf(
        Question(R.string.a, true),
        Question(R.string.b, false),
        Question(R.string.c, true),
        Question(R.string.d, false),
        Question(R.string.e, false),
        Question(R.string.f, true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question)
        answerTextView = findViewById(R.id.answer)

        answerTextView.visibility = View.INVISIBLE
        updateQuestion()

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        nextButton.setOnClickListener {
            answerTextView.visibility = View.INVISIBLE
            if (currentQuestionIndex < questionBank.size) {
                currentQuestionIndex++
                if (currentQuestionIndex == questionBank.size) {
                    nextButton.visibility = View.GONE
                    prevButton.visibility = View.GONE
                    trueButton.visibility = View.GONE
                    falseButton.visibility = View.GONE
                    questionTextView.text = "Your Score: $correct/${questionBank.size}"
                } else {
                    updateQuestion()
                }
            }
        }

        prevButton.setOnClickListener {
            answerTextView.visibility = View.INVISIBLE
            if (currentQuestionIndex > 0) {
                currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.size
                updateQuestion()
            }
        }
    }

    private fun updateQuestion() {
        questionTextView.setText(questionBank[currentQuestionIndex].answerResId)
    }

    private fun checkAnswer(userChoseTrue: Boolean) {
        val correctAnswer = questionBank[currentQuestionIndex].isAnswerTrue
        val message = if (userChoseTrue == correctAnswer) {
            correct++
            "That's correct"
        } else {
            "That's incorrect"
        }

        answerTextView.visibility = View.VISIBLE
        answerTextView.text = message
    }
}
