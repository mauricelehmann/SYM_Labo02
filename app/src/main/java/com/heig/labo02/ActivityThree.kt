package com.heig.labo02

import android.annotation.SuppressLint
import android.os.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.heig.labo02.business.Person
import com.heig.labo02.comm.CommunicationEventListener
import com.heig.labo02.comm.SymComManager
import kotlin.concurrent.thread

class ActivityThree : AppCompatActivity() {

    private lateinit var activityThreeButton: Button

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_three)

        // Affichage du message reçu
        val responseBox = findViewById<TextView>(R.id.activity_three_text)
        activityThreeButton = findViewById(R.id.activity_three_button)

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Toast.makeText(applicationContext, "is dis answer ?", Toast.LENGTH_SHORT)
                    .show()
                responseBox.text = msg.data.getString("FROM_SERVER")
            }
        }

        activityThreeButton.setOnClickListener {

            val p1 = Person("momo", 26)
            val gson = Gson()
            val personjson = gson.toJson(p1)

            // Send http req here
            Toast.makeText(applicationContext, "request is coming", Toast.LENGTH_SHORT).show()
            responseBox.text = "En attente de la réponse"

            thread {
                val mcm = SymComManager(
                    object : CommunicationEventListener {
                        override fun handleServerResponse(response: String): Boolean {

                            // Deserialize the Person object as Json
                            val responseAsPerson = gson.fromJson(response, Person::class.java)

                            val msg: Message = handler.obtainMessage()
                            val b = Bundle()
                            b.putString("FROM_SERVER", responseAsPerson.toString())
                            msg.data = b
                            handler.sendMessage(msg)
                            return true
                        }
                    }
                )
                //Send a serialized Person object as Json
                mcm.sendRequest( "http://sym.iict.ch/rest/json", personjson, "application/json")
            }
        }
    }
}
