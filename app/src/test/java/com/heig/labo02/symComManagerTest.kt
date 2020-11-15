package com.heig.labo02

import com.google.gson.Gson
import com.heig.labo02.business.Person
import com.heig.labo02.comm.CommunicationEventListener
import com.heig.labo02.comm.SymComManager
import org.json.JSONStringer
import org.json.JSONTokener
import org.junit.Test


import org.junit.Assert.*
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
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

        val comManager = SymComManager();
        comManager.sendRequest("http://sym.iict.ch/rest/txt", "toto", "txt/plain");

        assertEquals(4, 2 + 2)

    }
}