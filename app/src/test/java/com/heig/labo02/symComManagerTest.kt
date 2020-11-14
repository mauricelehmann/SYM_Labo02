package com.heig.labo02

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.TextView
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
        comManager.sendRequest( "http://sym.iict.ch/rest/txt", "toto", "txt/plain");

        assertEquals(4, 2 + 2)

    }

    // TODO : Vrai tests mdr ou aucuns ! 

    @Test
    fun serialize() {
        val p1 = Person("momo", 26)
        println(p1.age)
        println(p1.name)

        val gson = Gson()
        val personjson = gson.toJson(p1)
        println(personjson)
    }

    @Test
    fun deserialize() {
        val p1 = "{\"name\":\"momo\",\"age\":26}"
        val gson = Gson()
        val personFromJson = gson.fromJson(p1, Person::class.java)
        println(personFromJson.toString())
    }

    @Test
    fun sendJson(){
        val p1 = Person("momo", 26)
        val gson = Gson()
        val personjson = gson.toJson(p1)


        val url = URL("http://sym.iict.ch/rest/json")
        val request = personjson

        try {
            var response = ""

            with(url.openConnection() as HttpURLConnection) {
                setRequestProperty("Content-Type", "application/json")
                requestMethod = "POST"
                doOutput = true
                outputStream.write(request.toByteArray()) //Pas oublier d'envoyer le payload!

                println("\nSent '$request' request to URL : $url; Response Code : $responseCode")

                inputStream.bufferedReader().use {
                    it.lines().forEach {line ->
                        response += line + '\n'
                    }
                }

                val jsonResp = gson.fromJson(response, Person::class.java)
            }
        } catch (e: Exception) {
            print(e.message)
        }
    }
}