/*
 -----------------------------------------------------------------------------------
 Laboratoire :  02
 Fichier     :  ActivityFive.kt
 Auteur(s)   :  Maurice Lehmann
 -----------------------------------------------------------------------------------
 */

package com.heig.labo02

import android.os.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.heig.labo02.business.Person
import com.heig.labo02.comm.CommunicationEventListener
import com.heig.labo02.comm.SymComManager
import kotlin.concurrent.thread

/**
 * Compression - Sending a compress JSON object
 */
class ActivityFive: AppCompatActivity() {
    private lateinit var activityFiveButton: Button
    private lateinit var responseBox: TextView
    private val SRV_TAG = "SERVER_MESSAGE"

    private val person = Person("maurice",
        "lehmann",
        "homme",
        "079 151 62 52",
        "home")

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five)

        responseBox = findViewById(R.id.activity_five_text)
        activityFiveButton = findViewById(R.id.activity_five_button)


        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Toast.makeText(applicationContext, "is dis answer ?", Toast.LENGTH_SHORT)
                    .show()
                responseBox.text = msg.data.getString(SRV_TAG)
            }
        }

        activityFiveButton.setOnClickListener {
            Toast.makeText(applicationContext, "request is coming", Toast.LENGTH_SHORT).show()
            responseBox.text = "En attente de la réponse"

            thread {
                val mcm = SymComManager(
                    object : CommunicationEventListener {
                        override fun handleServerResponse(response: String): Boolean {

                            // Deserialize the Person object as Json
                            val responseAsPerson = Person.fromJson(response)

                            val msg: Message = handler.obtainMessage()
                            val bundle = Bundle()
                            bundle.putString(SRV_TAG, responseAsPerson.toString())
                            msg.data = bundle
                            handler.sendMessage(msg)
                            return true
                        }
                    }
                )
                //Send a serialized Person object as Json
                mcm.sendRequest(
                    "http://sym.iict.ch/rest/json",
                    Person.toJson(person),
                    "application/json",
                    true
                )
            }
        }
    }

    companion object {
        fun compressData(data : String){

        }
    }
}