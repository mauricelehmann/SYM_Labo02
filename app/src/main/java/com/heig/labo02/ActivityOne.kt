package com.heig.labo02

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
//        comManager.communicationEventListener = object : CommunicationEventListener {
//            override fun handleServerResponse(response: String): Boolean {
//                responseBox.text = response
//                return true
//            }
//        }

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                val toast = Toast.makeText(applicationContext, "is dis answer ?", Toast.LENGTH_SHORT)
                toast.show()
                responseBox.text = msg.data.getString("FROM_SERVER")
            }
        }

        activityOneButton.setOnClickListener {
            // Send http req here
            val toast = Toast.makeText(applicationContext, "Yee", Toast.LENGTH_SHORT)
            toast.show()
            responseBox.text = "En attente de la réponse"
            // comManager.sendRequest( "http://sym.iict.ch/rest/txt", "toto");

            thread (start = true) {
                val mcm = com.heig.labo02.comm.SymComManager(
                object : CommunicationEventListener {
                    override fun handleServerResponse(response: String): Boolean {
                        val msg: Message = handler.obtainMessage()
                        var b = Bundle()
                        b.putString("FROM_SERVER", response)
                        msg.data = b
                        handler.sendMessage(msg)
                        return true
                    }
                }
            )
            mcm.sendRequest( "http://sym.iict.ch/rest/txt", "i wuv kt")
            }
        }
    }
    companion object {
        private val comManager = SymComManager()
    }

}
