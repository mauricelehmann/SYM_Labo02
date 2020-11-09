package com.heig.labo02.comm

class SymComManager(var communicationEventListener: CommunicationEventListener? = null) {

    fun sendRequest(url: String, request: String) {
		// Envoyer la requete à l'URL...

        // some code...

        // Envoyer la réponse au communicationEventListener
        val response = ""

        // some code...

        communicationEventListener?.handleServerResponse(response)
    }

}