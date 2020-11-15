package com.heig.labo02

import android.os.*
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.heig.labo02.business.Author
import com.heig.labo02.comm.CommunicationEventListener
import com.heig.labo02.comm.SymComManager
import kotlin.concurrent.thread


/**
 * Send GraphQL queries
 */
class ActivityFour: AppCompatActivity() {
    private lateinit var activityTwoButton: Button
    private lateinit var responseBox: TextView
    private lateinit var spinner: Spinner

    private val SRV_TAG = "SERVER_MESSAGE"
    private var  allAuthors = arrayOfNulls<String>(1)
    private var  allAuthorsId = arrayOfNulls<String>(1)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)

        responseBox = findViewById(R.id.activity_four_text)
        activityTwoButton = findViewById(R.id.activity_four_button)
        spinner = findViewById(R.id.spinner2)

        loadAuthor()


        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {

                allAuthorsId[position]?.let { printAuthors(it) }
                Toast.makeText(applicationContext, allAuthors[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        activityTwoButton.setOnClickListener {
                Toast.makeText(applicationContext, "request is coming", Toast.LENGTH_SHORT).show()
                responseBox.text = "En attente de la réponse"

            }
        }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadAuthor(){

        var query = "{\"query\" : \"{allAuthors{id first_name last_name}}\"}"

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Toast.makeText(applicationContext, "Loading...", Toast.LENGTH_SHORT)
                    .show()
                var data = msg.data.getString(SRV_TAG).toString()

                // Parsing du json as string
                val gson = Gson()
                data = data.removeRange(0, 22)
                data = data.removeRange(data.length - 3, data.length)
                var authors: Array<Author> = gson.fromJson(data, Array<Author>::class.java)

                addAuthorsToSpinner(authors)

            }
        }
        thread {
            val mcm = SymComManager(
                object : CommunicationEventListener {
                    override fun handleServerResponse(response: String): Boolean {
                        val msg: Message = handler.obtainMessage()
                        val b = Bundle()
                        b.putString(SRV_TAG, response)
                        msg.data = b
                        handler.sendMessage(msg)
                        return true
                    }
                }
            )

            //Send a serialized Person object as Json
            mcm.sendRequest( "http://sym.iict.ch/api/graphql", query, "application/json", false)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun printAuthors(id : String){

        var query =
            "{\"query\" : \"{author(id : $id){posts{title}}}\"}"

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Toast.makeText(applicationContext, "Loading...", Toast.LENGTH_SHORT)
                    .show()
                responseBox.text = msg.data.getString(SRV_TAG)
            }
        }
        thread {
            val mcm = SymComManager(
                object : CommunicationEventListener {
                    override fun handleServerResponse(response: String): Boolean {
                        val msg: Message = handler.obtainMessage()
                        val b = Bundle()
                        b.putString(SRV_TAG, response)
                        msg.data = b
                        handler.sendMessage(msg)
                        return true
                    }
                }
            )

            //Send a serialized Person object as Json
            mcm.sendRequest( "http://sym.iict.ch/api/graphql", query, "application/json", false)
        }
    }

    private fun addAuthorsToSpinner(authors: Array<Author>) {

        allAuthors = arrayOfNulls<String>(authors.size)
        allAuthorsId = arrayOfNulls<String>(authors.size)

        for (i in allAuthors.indices) {
            allAuthors[i] = authors[i].first_name + " " + authors[i].last_name
            allAuthorsId[i] = authors[i].id
        }

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, allAuthors)
        spinner.adapter = adapter
    }
}