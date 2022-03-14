package com.iam2kabhishek.evaltrivia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val startButton = findViewById<Button>(R.id.button_start)
        startButton.setOnClickListener {
            val intent = Intent(this, TriviaActivity::class.java)
            startActivity(intent)
        }

        val aboutButton = findViewById<Button>(R.id.button_about)
        aboutButton.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}