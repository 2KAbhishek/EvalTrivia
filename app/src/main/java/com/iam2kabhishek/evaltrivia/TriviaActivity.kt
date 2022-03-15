package com.iam2kabhishek.evaltrivia

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iam2kabhishek.evaltrivia.expr.Expression
import com.iam2kabhishek.evaltrivia.expr.Maker
import com.iam2kabhishek.evaltrivia.expr.Solver

class TriviaActivity : AppCompatActivity() {
    private var timerCount = 60
    private val expressions: List<Expression> = Maker().generateRandomExpressions
    private var expressionIndex = 0
    private var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)

        val exprOneText = findViewById<TextView>(R.id.expr_one_text)
        val exprTwoText = findViewById<TextView>(R.id.expr_two_text)

        val greaterButton = findViewById<Button>(R.id.greater_button)
        val equalButton = findViewById<Button>(R.id.equal_button)
        val lesserButton = findViewById<Button>(R.id.lesser_button)

        greaterButton.setOnClickListener{
            val expr1 = exprOneText.text.toString()
            val expr2 = exprTwoText.text.toString()
            checkAnswer(expr1, expr2, '>')
        }

        equalButton.setOnClickListener{
            val expr1 = exprOneText.text.toString()
            val expr2 = exprTwoText.text.toString()
            checkAnswer(expr1, expr2, '=')
        }

        lesserButton.setOnClickListener{
            val expr1 = exprOneText.text.toString()
            val expr2 = exprTwoText.text.toString()
            checkAnswer(expr1, expr2, '<')
        }
        startTimeCounter()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        val exprOneText = findViewById<TextView>(R.id.expr_one_text).text.toString()
        val exprTwoText = findViewById<TextView>(R.id.expr_two_text).text.toString()
        val timerText = findViewById<TextView>(R.id.timer_text).text.toString()

        savedInstanceState.putString("exprOneText", exprOneText)
        savedInstanceState.putString("exprTwoText", exprTwoText)
        savedInstanceState.putString("timerText", timerText)

        savedInstanceState.putInt("correctAnswers", correctAnswers)
        savedInstanceState.putInt("expressionIndex", expressionIndex)
        savedInstanceState.putInt("timerCount", timerCount)

        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val exprOneText = findViewById<TextView>(R.id.expr_one_text)
        val exprTwoText = findViewById<TextView>(R.id.expr_two_text)
        val timerText = findViewById<TextView>(R.id.timer_text)

        exprOneText.text = savedInstanceState.getString("exprOneText")
        exprTwoText.text = savedInstanceState.getString("exprTwoText")
        timerText.text = savedInstanceState.getString("timerText")

        timerCount = savedInstanceState.getInt("timerCount")
        correctAnswers = savedInstanceState.getInt("correctAnswers")
        expressionIndex = savedInstanceState.getInt("expressionIndex")
    }

    private fun startTimeCounter() {
        val timerText = findViewById<TextView>(R.id.timer_text)
        object : CountDownTimer((timerCount * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = timerCount.toString()
                timerCount--
            }
            override fun onFinish() {
                if (timerCount <= 1) {
                    resultActivity()
                }
            }
        }.start()
    }

    private fun resultActivity() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("CORRECT", correctAnswers)
        val questionsAttempted = if (expressionIndex == 0) 1 else expressionIndex / 2
        intent.putExtra("INCORRECT", questionsAttempted - correctAnswers)
        finish()
        startActivity(intent)
    }

    private fun checkAnswer(expr1: String, expr2: String, c: Char) {
        when (c) {
            '>' -> correctAnswers += if (Solver.solve(expr1) > Solver.solve(expr2)) 1 else 0
            '=' -> correctAnswers += if (Solver.solve(expr1) == Solver.solve(expr2)) 1 else 0
            '<' -> correctAnswers += if (Solver.solve(expr1) < Solver.solve(expr2)) 1 else 0
        }
        val exprOneText = findViewById<TextView>(R.id.expr_one_text)
        val exprTwoText = findViewById<TextView>(R.id.expr_two_text)
        exprOneText.text = expressions[expressionIndex].toString()
        exprTwoText.text = expressions[expressionIndex + 1].toString()
        expressionIndex += 2
    }
}