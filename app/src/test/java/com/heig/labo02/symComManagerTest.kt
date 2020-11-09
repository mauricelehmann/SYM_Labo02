package com.heig.labo02

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.heig.labo02.comm.CommunicationEventListener
import com.heig.labo02.comm.SymComManager
import org.junit.Test

import org.junit.Assert.*


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SymComManagerTest {
    @Test
    fun symComManagerTesting() {

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
    }
}