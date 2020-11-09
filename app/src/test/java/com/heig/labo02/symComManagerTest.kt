package com.heig.labo02

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import com.heig.labo02.comm.CommunicationEventListener
import com.heig.labo02.comm.SymComManager
import org.junit.Test


import org.junit.Assert.*
import java.lang.ref.WeakReference
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.concurrent.thread


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SymComManagerTest {
    @Test
    fun symComManagerTesting() {
        /*
        val comManager = SymComManager();

        val handler = object:  Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                // console out message
            }
        }

        // Start new thread
        comManager.communicationEventListener = object : CommunicationEventListener {
            override fun handleServerResponse(response: String) {

                // envoyer la réponse au handler
            }
        };

        comManager.sendRequest("", "");


        assertEquals(4, 2 + 2)

        */

        val handler = object: Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                // console out message
                print(msg.data.getString("TRUC"))
            }
        }

        thread (start = true) {
            val mcm = com.heig.labo02.comm.SymComManager(
                object : CommunicationEventListener {
                    override fun handleServerResponse(response: String): Boolean {
                        val msg: Message = handler.obtainMessage()
                        val b: Bundle ?= null
                        b?.putString("FROM_SERVER", response)
                        msg.data = b
                        handler.sendMessage(msg)
                        return true
                    }
                }
            )
            mcm.sendRequest( "http://sym.iict.ch/rest/txt", "toto")
        }
    }
}