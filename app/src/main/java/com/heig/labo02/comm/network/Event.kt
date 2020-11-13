package com.heig.labo02.comm.network

sealed class Event {
    class ConnectivityEvent(val isConnected: Boolean) : Event()
}