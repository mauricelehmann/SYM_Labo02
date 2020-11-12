package com.heig.labo02.comm.network

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

interface NetworkState {
    val isAvailable: Boolean
    val network: Network?
    val networkCapabilities: NetworkCapabilities?
    val linkProperties: LinkProperties?

    val isWifi: Boolean
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false
    val isMobile: Boolean
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false
    val interfaceName: String?
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        get() = linkProperties?.interfaceName
}