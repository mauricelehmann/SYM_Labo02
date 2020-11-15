package com.heig.labo02.comm.network

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi


interface NetworkState {

    val isAvailable: Boolean

    /**
     * The network being used by the device
     */
    val network: Network?

    /**
     * Network Capabilities
     */
    val networkCapabilities: NetworkCapabilities?

    /**
     * Link Properties
     */
    val linkProperties: LinkProperties?

    /**
     * Check if the network is Wifi ( shortcut )
     */
    val isWifi: Boolean
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false

    /**
     * Check if the network is Mobile ( shortcut )
     */
    val isMobile: Boolean
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false

    /**
     * Get the interface name ( shortcut )
     */
    val interfaceName: String?
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = linkProperties?.interfaceName
}