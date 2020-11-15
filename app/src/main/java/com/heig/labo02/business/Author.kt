package com.heig.labo02.business

class Author(){
    var id: String = ""
    var first_name: String = ""
    var last_name : String = ""
    constructor(_id : String , _firstname : String, _lastname : String) : this() {
        id = _id
        first_name = _firstname
        last_name = _lastname
    }
}