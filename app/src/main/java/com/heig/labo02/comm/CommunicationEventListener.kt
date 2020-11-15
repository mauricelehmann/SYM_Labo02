/*
 -----------------------------------------------------------------------------------
 Laboratoire :  02
 Fichier     :  CommunicationEventListener.kt
 Auteur(s)   :  Maurice Lehmann
 -----------------------------------------------------------------------------------
 */

package com.heig.labo02.comm

interface CommunicationEventListener {
    fun handleServerResponse(response :String): Boolean
}