package com.heig.labo02.comm.network.core

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import com.heig.labo02.comm.network.NetworkState

internal class NetworkStateImp: NetworkState {
    override var isAvailable: Boolean = false
    override var network: Network? = null
    override var linkProperties: LinkProperties? = null
    override var networkCapabilities: NetworkCapabilities? = null
}