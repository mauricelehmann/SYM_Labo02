package com.heig.labo02

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.heig.labo02.comm.CommunicationEventListener
import com.heig.labo02.comm.SymComManager

class ActivityOne : AppCompatActivity() {

    private lateinit var activityOneButton: Button

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_one)

        // Affichage du message reçu
        val responseBox = findViewById<TextView>(R.id.activity_one_text)
        activityOneButton = findViewById(R.id.activity_one_button)


        // Definition du listener du communication manager
        comManager.communicationEventListener = object : CommunicationEventListener {
            override fun handleServerResponse(response: String): Boolean {
                responseBox.setText(response)
                return true
            }
        }
        activityOneButton.setOnClickListener {
            // Send http req here
            val toast = Toast.makeText(applicationContext, "Yee", Toast.LENGTH_SHORT)
            toast.show()
            comManager.sendRequest( "https://sym.iict.ch/rest/txt", "toto");
        }
    }
    companion object {
        private val comManager = SymComManager()
    }

}
