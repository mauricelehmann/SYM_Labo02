package com.heig.labo02.comm

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class SymComManager(var communicationEventListener: CommunicationEventListener? = null) {

    @RequiresApi(Build.VERSION_CODES.N)
    fun sendRequest(_url: String, _request: String) {
        try {
            val url = URL(_url)

            var response = ""

            with(url.openConnection() as HttpURLConnection) {
                setRequestProperty("Content-Type", "txt/plain")
                requestMethod = "POST"
                doOutput = true

                println("\nSent '$_request' request to URL : $url; Response Code : $responseCode")

                inputStream.bufferedReader().use {
                    it.lines().forEach {line ->
                        response += line + '\n'
                    }
                }

                communicationEventListener?.handleServerResponse(response)
            }
        } catch (e: Exception) {
            print(e.message)
        }
    }

    companion object {
        private const val TAG: String = "SymComManager"
    }
}