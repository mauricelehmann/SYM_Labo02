package com.heig.labo02.business

class Person(var name : String , var age : Int) {

    override fun toString(): String {
        return "Name : $name\nAge  : $age";
    }
}