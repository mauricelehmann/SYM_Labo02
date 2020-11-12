package com.heig.labo02

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityOne : AppCompatActivity() {

    private lateinit var activityOneButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_one)

        activityOneButton = findViewById(R.id.activity_one_button)
        activityOneButton.setOnClickListener {
          // Send http req here
            val toast = Toast.makeText(applicationContext, "Welcome ", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

}
