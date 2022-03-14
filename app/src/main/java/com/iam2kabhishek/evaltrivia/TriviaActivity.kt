package com.iam2kabhishek.evaltrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView

class TriviaActivity : AppCompatActivity() {
    var timerCount = 50
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)
    }

    fun startTimeCounter(view: View) {
        val timerText = findViewById<TextView>(R.id.timer_text)
        object : CountDownTimer(49999, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = timerCount.toString()
                timerCount++
            }
            override fun onFinish() {
                timerText.text = "Game Over!"
            }
        }.start()
    }
}