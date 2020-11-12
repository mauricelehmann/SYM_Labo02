package com.heig.labo02

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityTwo: AppCompatActivity() {
    private lateinit var activityTwoButton: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_two)

        val responseBox = findViewById<TextView>(R.id.activity_two_text)
        val activityTwoButton = findViewById<Button>(R.id.activity_two_button)

        activityTwoButton.setOnClickListener {
            Toast.makeText(applicationContext, "request is coming", Toast.LENGTH_SHORT).show()

            responseBox.text = "En attente de la réponse"
        }
    }

    companion object {
        private var waitingList = mutableListOf<String>()
    }
}