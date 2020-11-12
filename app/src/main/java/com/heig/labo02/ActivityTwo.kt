package com.heig.labo02

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Delayed
import kotlin.concurrent.thread

class ActivityTwo: AppCompatActivity() {

    private lateinit var sendBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        sendBtn = findViewById(R.id.send_request)
        sendBtn.setOnClickListener {
            val thread = thread {
                var context: Context = Delayed

            }
        }
    }
}