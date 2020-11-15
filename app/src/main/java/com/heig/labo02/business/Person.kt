/*
 -----------------------------------------------------------------------------------
 Laboratoire :  02
 Fichier     :  Person.kt
 Auteur(s)   :  Maurice Lehmann
 -----------------------------------------------------------------------------------
 */

package com.heig.labo02.business

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText
import com.google.gson.Gson

class Phone(){
    @JacksonXmlText
    var number : String = ""
    @JacksonXmlProperty(localName = "type", isAttribute = true)
    var type : String = ""
    constructor(_number : String , _type : String) : this() {
        number = _number
        type = _type
    }
}
@JsonPropertyOrder(*["name", "firstname", "gender", "phone" ])
class Person() {

    var name: String = ""
    var firstname: String = ""
    var gender : String = ""
    lateinit var phone : Phone

    constructor(_name : String , _firstname : String , _gender : String,
                _phone : String, _type : String) : this() {
        name = _name
        firstname = _firstname
        gender = _gender
        phone = Phone(_phone, _type)
    }

    override fun toString(): String {
        return "Name : $name\nFirstname : $firstname\nGender : $gender\nPhone : ${phone.number}\n"
    }

    companion object {
        fun toJson(person : Person): String {
            val gson = Gson()
            val personjson = gson.toJson(person)
            return personjson
        }
        fun fromJson(personJson : String) : Person {
            val gson = Gson()
            return gson.fromJson(personJson, Person::class.java)
        }
        fun toXML(person : Person) : String {
            val xmlMapper = XmlMapper()
            var ret = xmlMapper.writeValueAsString(person)
            ret = ret.replace("<Person>", "<person>")
            ret = ret.replace("</Person>", "</person>")
            return ret
        }
        fun fromXML(personXML : String) : Person {
            val xmlMapper = XmlMapper()
            return xmlMapper.readValue(personXML, Person::class.java)
        }
    }
}