package com.heig.labo02

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var activityOneButton: Button
    private lateinit var activityTwoButton: Button
    private lateinit var activityThreeButton: Button
    private lateinit var activityFourButton: Button
    private lateinit var activityFiveButton: Button


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
         * Activity two - Service de transmission différé
         */
        activityTwoButton = findViewById(R.id.main_activity_two)
        activityTwoButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ActivityTwo::class.java)
            startActivity(intent)
        }

        /**
         * Activity three - Service de transmission XML et JSON
         */
        activityThreeButton = findViewById(R.id.main_activity_three)
        activityThreeButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ActivityThree::class.java)
            startActivity(intent)
        }

        /**
         * Activity four - Service de transmission GraphQl
         */
        activityFourButton = findViewById(R.id.main_activity_four)
        activityFourButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ActivityThree::class.java)
            startActivity(intent)
        }

        /**
         * Activity five - Service de transmission compressé
         */
        activityFiveButton = findViewById(R.id.main_activity_five)
        activityFiveButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ActivityThree::class.java)
            startActivity(intent)
        }
    }
}
