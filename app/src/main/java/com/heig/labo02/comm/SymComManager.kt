/*
 -----------------------------------------------------------------------------------
 Laboratoire :  02
 Fichier     :  SymComManager.kt
 Auteur(s)   :  Maurice Lehmann
 -----------------------------------------------------------------------------------
 */

package com.heig.labo02.comm

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.zip.Deflater
import java.util.zip.DeflaterOutputStream
import java.util.zip.Inflater
import java.util.zip.InflaterInputStream

class SymComManager(var communicationEventListener: CommunicationEventListener? = null) {

    @RequiresApi(Build.VERSION_CODES.N)
    fun sendRequest(_url: String, _request: String, _contentType : String, _doCompress : Boolean) {


        try {
            val url = URL(_url)

            var response = ""

            val connection=  url.openConnection() as HttpURLConnection
            connection.setRequestProperty("Content-Type", _contentType)
            connection.requestMethod = "POST"
            connection.doOutput = true
            var output : OutputStream

            if(_doCompress) {
                connection.setRequestProperty("X-Network", "CSD") // network speed limitation
                connection.setRequestProperty("X-Content-Encoding", "deflate") // Enable compression
                output = DeflaterOutputStream(connection.outputStream, Deflater(9, true))
            }else{
                output = connection.outputStream
            }
            output.write(_request.toByteArray()) //Send the payload
            println("\nSent '$_request' request to URL : $url; Response Code : ${connection.responseCode}")

            if(_doCompress) {
                var compressInput = BufferedReader(
                    InputStreamReader(
                        InflaterInputStream(connection.inputStream, Inflater(true)),
                        StandardCharsets.UTF_8)
                )
                compressInput.buffered().use {
                    it.lines().forEach { line ->
                        response += line + '\n'
                    }
                }

            }else {
                connection.inputStream.bufferedReader().use {
                    it.lines().forEach { line ->
                        response += line + '\n'
                    }
                }
            }
            println("RESPONSE : $response")
            Log.i(TAG, response)
            communicationEventListener?.handleServerResponse(response)

        } catch (e: Exception) {
            print(e.message)
        }
    }

    companion object {
        private const val TAG: String = "SymComManager"
    }
}