/*
 -----------------------------------------------------------------------------------
 Laboratoire :  02
 Fichier     :  ActivityTwo.kt
 Auteur(s)   :  Soulaymane Lamrani
 -----------------------------------------------------------------------------------
 */

package com.heig.labo02

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.heig.labo02.comm.CommunicationEventListener
import com.heig.labo02.comm.SymComManager
import com.heig.labo02.comm.network.NetworkMonitorUtil
import kotlin.concurrent.thread


class ActivityTwo: AppCompatActivity() {
    private lateinit var activityTwoButton: Button
    private lateinit var responseBox: TextView

    private var sendThread = Thread()
    private var counter = 0
    private val networkMonitor = NetworkMonitorUtil(this)

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        responseBox = findViewById(R.id.activity_two_text)
        activityTwoButton = findViewById(R.id.activity_two_button)

        sendThread = thread (start = false) {
            while (true) {
                if (networkMonitor.isAvailable) {
                    val mcm = SymComManager(
                        object : CommunicationEventListener {
                            override fun handleServerResponse(response: String): Boolean {
                                println(response)
                                return true
                            }
                        }
                    )

                    for (message in waitingList) {
                        println("sending **$message**")
                        mcm.sendRequest(
                            "http://sym.iict.ch/rest/txt",
                            message,
                            "txt/plain",
                            false
                        )
                    }

                    waitingList.clear()

                    break
                }

                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

        activityTwoButton.setOnClickListener {
            counter++

            // Problem : when we  press the send button more than once, there is an
            // IllegalThreadStateException due to the sendThread.start()
            // Investigation is still occuring
            try {
                if (!sendThread.isAlive) sendThread.start()
            } catch (e: IllegalThreadStateException) {
                e.printStackTrace()
            }

            waitingList.add("me want to go $counter !")
        }
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }

    companion object {
        private var waitingList = mutableListOf<String>()
    }
}