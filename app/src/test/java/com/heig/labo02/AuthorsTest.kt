package com.heig.labo02

import com.google.gson.Gson
import com.heig.labo02.business.Author
import org.junit.Test

class AuthorsTest {

    var data = "{\"data\": {\"allAuthors\": [{\"id\": \"1\",\"first_name\": \"Kailee\",\"last_name\": \"Zulauf\" },{\"id\": \"2\",\"first_name\": \"Mariela\",\"last_name\": \"Dach\" }]}}"
    var data2 = "[{\"id\": \"1\",\"first_name\": \"Kailee\",\"last_name\": \"Zulauf\" },{\"id\": \"2\",\"first_name\": \"Mariela\",\"last_name\": \"Dach\" }]"

    @Test
    fun parseAuthors() {
        val gson = Gson()

        data = data.removeRange(0, 24)
        data = data.removeRange(data.length - 2, data.length)

        val authors: Array<Author> = gson.fromJson(data, Array<Author>::class.java)

        for (a in authors){
            println(a.id)
            println(a.last_name)
            println(a.first_name)
        }
    }
}

