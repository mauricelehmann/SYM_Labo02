package com.heig.labo02

import android.annotation.SuppressLint
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

class ActivityThree : AppCompatActivity() {

    private lateinit var sendJSONObjectButton: Button
    private lateinit var sendXMLObjectButton: Button

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
                responseBox.text = msg.data.getString("FROM_SERVER")
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
                            val b = Bundle()
                            b.putString("FROM_SERVER", responseAsPerson.toString())
                            msg.data = b
                            handler.sendMessage(msg)
                            return true
                        }
                    }
                )
                //Send a serialized Person object as Json
                mcm.sendRequest( "http://sym.iict.ch/rest/json", Person.toJson(person), "application/json")
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

            println(payload)

            thread {
                val mcm = SymComManager(
                    object : CommunicationEventListener {
                        override fun handleServerResponse(response: String): Boolean {

                            // Deserialize the Person object as Json TODO : virer les fields qui servent a rien !
                            // val responseAsPerson = Person.fromXML(response)

                            val msg: Message = handler.obtainMessage()
                            val b = Bundle()
                            b.putString("FROM_SERVER", response)
                            msg.data = b
                            handler.sendMessage(msg)
                            return true
                        }
                    }
                )


                payload = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<!DOCTYPE directory SYSTEM \"http://sym.iict.ch/directory.dtd\">\n" +
                        "<directory>\n" +
                        "<person>\n" +
                        "    <name>maurice</name>\n" +
                        "    <firstname>lehmann</firstname>\n" +
                        "    <gender>homme</gender>\n" +
                        "    <phone type=\"home\">079 151 62 52</phone>\n" +
                        "</person>\n" +
                        "</directory>"
                //Send a serialized Person object as Json
                mcm.sendRequest( "http://sym.iict.ch/rest/xml", payload, "application/xml")
            }
        }
    }
}
