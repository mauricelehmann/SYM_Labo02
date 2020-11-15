package com.heig.labo02

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Send GraphQL queries
 */
class ActivityFour: AppCompatActivity() {
    private lateinit var activityTwoButton: Button
    private lateinit var responseBox: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)

        responseBox = findViewById(R.id.activity_four_text)
        activityTwoButton = findViewById(R.id.activity_four_button)

        activityTwoButton.setOnClickListener {
            Toast.makeText(applicationContext, "request is coming", Toast.LENGTH_SHORT).show()

            responseBox.text = "En attente de la réponse"

        }
    }
}