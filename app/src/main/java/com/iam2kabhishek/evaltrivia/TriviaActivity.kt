package com.iam2kabhishek.evaltrivia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import com.iam2kabhishek.evaltrivia.expr.Expression
import com.iam2kabhishek.evaltrivia.expr.Maker

class TriviaActivity : AppCompatActivity() {
    var timerCount = 50
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)
        val expressions = getExpressions()
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

    private fun getExpressions(): List<Expression> {
        val maker = Maker()
        return maker.generateRandomExpressions
    }
}