package com.heig.labo02


import com.heig.labo02.business.Person
import com.heig.labo02.comm.SymComManager
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.zip.Deflater
import java.util.zip.DeflaterOutputStream
import java.util.zip.Inflater
import java.util.zip.InflaterInputStream


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SymComManagerTest {


    @Test
    fun symComManagerTesting() {

        val comManager = SymComManager();
        comManager.sendRequest("http://sym.iict.ch/rest/txt", "toto", "txt/plain", false);

        assertEquals(4, 2 + 2)

    }
    @Test
    fun sendCompressData(){

    }
}