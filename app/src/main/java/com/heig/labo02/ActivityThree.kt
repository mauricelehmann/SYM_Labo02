package com.heig.labo02

import android.annotation.SuppressLint
import android.os.*
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.heig.labo02.business.Person
import com.heig.labo02.comm.CommunicationEventListener
import com.heig.labo02.comm.SymComManager
import kotlin.concurrent.thread

class ActivityThree : AppCompatActivity() {

    private lateinit var sendJSONObjectButton: Button
    private lateinit var sendXMLObjectButton: Button
    private val SRV_TAG = "SERVER_MESSAGE"

    private val person = Person("maurice", "lehmann", "homme", "079 151 62 52", "home")

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_three)

        // Affichage du message reçu
        val responseBox = findViewById<TextView>(R.id.activity_three_text)
        sendJSONObjectButton = findViewById(R.id.sendJSONButton)
        sendXMLObjectButton = findViewById(R.id.sendXMLButton)

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Toast.makeText(applicationContext, "is dis answer ?", Toast.LENGTH_SHORT)
                    .show()
                responseBox.text = msg.data.getString(SRV_TAG)
            }
        }

        sendJSONObjectButton.setOnClickListener {

            // Send http req here
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
                mcm.sendRequest( "http://sym.iict.ch/rest/json", Person.toJson(person), "application/json", false)
            }
        }

        sendXMLObjectButton.setOnClickListener {
            // Send http req here
            Toast.makeText(applicationContext, "request is coming", Toast.LENGTH_SHORT).show()
            responseBox.text = "En attente de la réponse"



            var xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<!DOCTYPE directory SYSTEM \"http://sym.iict.ch/directory.dtd\">\n" +
                    "<directory>"
            var xmlPerson = Person.toXML(person)
            var payload = xmlHeader + xmlPerson + "</directory>"

            thread {
                val mcm = SymComManager(
                    object : CommunicationEventListener {
                        override fun handleServerResponse(response: String): Boolean {

                            val msg: Message = handler.obtainMessage()
                            val b = Bundle()
                            b.putString(SRV_TAG, response)
                            msg.data = b
                            handler.sendMessage(msg)
                            return true
                        }
                    }
                )

                //Send a serialized Person object as Json
                mcm.sendRequest( "http://sym.iict.ch/rest/xml", payload, "application/xml", false)
            }
        }
    }
}
