package com.heig.labo02

import android.annotation.SuppressLint
import android.os.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.heig.labo02.comm.CommunicationEventListener
import com.heig.labo02.comm.SymComManager
import kotlin.concurrent.thread

class ActivityOne : AppCompatActivity() {

    private lateinit var activityOneButton: Button
    private val SRV_TAG = "SERVER_MESSAGE"

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_one)

        // Affichage du message reçu
        val responseBox = findViewById<TextView>(R.id.activity_one_text)
        activityOneButton = findViewById(R.id.activity_one_button)

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Toast.makeText(applicationContext, "is dis answer ?", Toast.LENGTH_SHORT)
                    .show()
                responseBox.text = msg.data.getString(SRV_TAG)
            }
        }

        activityOneButton.setOnClickListener {
            // Send http req here
            Toast.makeText(applicationContext, "request is coming", Toast.LENGTH_SHORT).show()
            responseBox.text = "En attente de la réponse"

            thread {
                val mcm = SymComManager(
                    object : CommunicationEventListener {
                        override fun handleServerResponse(response: String): Boolean {
                            val msg: Message = handler.obtainMessage()
                            val bundle = Bundle()
                            bundle.putString(SRV_TAG, response)
                            msg.data = bundle
                            handler.sendMessage(msg)
                            return true
                        }
                    }
                )
                mcm.sendRequest( "http://sym.iict.ch/rest/txt", "i wuv kt", "txt/plain", false)
            }
        }
    }
}
