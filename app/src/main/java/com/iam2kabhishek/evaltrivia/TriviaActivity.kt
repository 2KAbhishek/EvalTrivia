package com.iam2kabhishek.evaltrivia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import com.iam2kabhishek.evaltrivia.expr.Expression
import com.iam2kabhishek.evaltrivia.expr.Maker
import com.iam2kabhishek.evaltrivia.expr.Solver

class TriviaActivity : AppCompatActivity() {
    private var timerCount = 50
    private val expressions: List<Expression> = Maker().generateRandomExpressions
    private var expressionIndex = 0
    private var score = 0

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

    private fun startTimeCounter() {
        val timerText = findViewById<TextView>(R.id.timer_text)
        object : CountDownTimer(49999, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = timerCount.toString()
                timerCount--
            }
            override fun onFinish() {
                timerText.text = "Game Over!"
                resultActivity()
            }
        }.start()
    }

    private fun resultActivity() {
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }

    private fun checkAnswer(expr1: String, expr2: String, c: Char) {
        when (c) {
            '>' -> score = if (Solver.solve(expr1) > Solver.solve(expr2)) score++ else score
            '=' -> score = if (Solver.solve(expr1) == Solver.solve(expr2)) score++ else score
            '<' -> score = if (Solver.solve(expr1) < Solver.solve(expr2)) score++ else score
        }
        val exprOneText = findViewById<TextView>(R.id.expr_one_text)
        val exprTwoText = findViewById<TextView>(R.id.expr_two_text)

        exprOneText.text = expressions[expressionIndex].toString()
        exprTwoText.text = expressions[expressionIndex + 1].toString()
        expressionIndex += 2
    }
}