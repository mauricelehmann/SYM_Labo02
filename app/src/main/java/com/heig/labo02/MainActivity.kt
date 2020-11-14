package com.heig.labo02

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var activityOneButton: Button
    private lateinit var activityThreeButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // For blockGuardPolicy
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        /**
         * Activity one - Service de transmission asynchrone
         */
        activityOneButton = findViewById(R.id.main_activity_one)
        activityOneButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ActivityOne::class.java)
            startActivity(intent)
        }

        /**
         * Activity one - Service de transmission asynchrone
         */
        activityOneButton = findViewById(R.id.main_activity_two)
        activityOneButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ActivityTwo::class.java)
            startActivity(intent)
        }

        /**
         * Activity one - Service de transmission asynchrone
         */
        activityThreeButton = findViewById(R.id.main_activity_three)
        activityThreeButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ActivityThree::class.java)
            startActivity(intent)
        }
    }
}
