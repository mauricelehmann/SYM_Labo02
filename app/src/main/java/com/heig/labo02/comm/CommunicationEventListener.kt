package com.heig.labo02.comm

interface CommunicationEventListener {
    fun handleServerResponse(response :String): Boolean
}