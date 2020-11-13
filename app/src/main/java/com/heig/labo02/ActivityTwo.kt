package com.heig.labo02

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.heig.labo02.comm.network.*

class ActivityTwo: AppCompatActivity(), NetworkConnectivityListener {
    private lateinit var activityTwoButton: Button
    private var previousState = true
    private lateinit var responseBox: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        responseBox = findViewById(R.id.activity_two_text)
        activityTwoButton = findViewById(R.id.activity_two_button)

        activityTwoButton.setOnClickListener {
            Toast.makeText(applicationContext, "request is coming", Toast.LENGTH_SHORT).show()

            responseBox.text = "En attente de la réponse"


        }
    }

    override fun networkConnectivityChanged(event: Event) {
        when (event) {
            is Event.ConnectivityEvent -> {
                if (event.isConnected) {
                    showSnackBar(responseBox, "The network is back !")
                } else {
                    showSnackBar(responseBox, "There is no more network")
                }
            }
        }
    }

    companion object {
        private var waitingList = mutableListOf<String>()
    }
}