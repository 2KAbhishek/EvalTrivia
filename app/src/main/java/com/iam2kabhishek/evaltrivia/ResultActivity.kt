package com.iam2kabhishek.evaltrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val correct = intent.getIntExtra("CORRECT", 1)
        val incorrect = intent.getIntExtra("INCORRECT", 1)

        val resultText = findViewById<TextView>(R.id.result_text)
        "You gave $correct correct and $incorrect incorrect answers".also { resultText.text = it }
    }
}