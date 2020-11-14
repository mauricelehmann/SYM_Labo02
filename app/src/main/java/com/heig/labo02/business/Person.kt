package com.heig.labo02.business

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.google.gson.Gson

class Person() {

    var name: String = ""
    var firstname: String = ""
    var gender : String = ""
    var phone : String = ""

    constructor(_name : String , _firstname : String , _gender : String, _phone : String) : this() {
        name = _name
        firstname = _firstname
        gender = _gender
        phone = _phone
    }

    override fun toString(): String {
        return "Name : $name\nFirstname : $firstname\nGender : $gender\nPhone : $phone\n"
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
            val ret = xmlMapper.writeValueAsString(person)
            return ret
        }
        fun fromXML(personXML : String) : Person {
            val xmlMapper = XmlMapper()
            return xmlMapper.readValue(personXML, Person::class.java)
        }
    }
}