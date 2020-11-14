package com.heig.labo02

import com.heig.labo02.business.Person
import org.junit.Test

class PersonTest {

    private val jsonPerson = "{\"name\":\"maurice\",\"firstname\":\"lehmann\",\"gender\":\"homme\",\"phone\":\"079 151 62 52\"}"
    private val XMLPerson = "<Person><name>maurice</name><firstname>lehmann</firstname><gender>homme</gender><phone type=\"home\">079 151 62 52</phone></Person>"
    private val p = Person("maurice", "lehmann", "homme", "079 151 62 52", "home")

    @Test
    fun serializePersonJSON() {
        val pToJson = Person.toJson(p)
        assert(pToJson == jsonPerson)
    }
    @Test
    fun deserializePersonJSON(){
        val pFromJson = Person.fromJson(jsonPerson)
        assert(p.toString() == pFromJson.toString())
    }

    @Test
    fun serializePersonXML() {
        val pToXML = Person.toXML(p)
        print(pToXML)
        assert(pToXML == XMLPerson)
    }
    @Test
    fun deserializePersonXML(){
        val pFromXML = Person.fromXML(XMLPerson)
        assert(p.toString() == pFromXML.toString())
    }
}