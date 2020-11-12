package com.heig.labo02.comm.network.core

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.heig.labo02.comm.network.core.NetworkStateImp

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
internal class NetworkCallbackImp(private val holder: NetworkStateImp):
    ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network) {
        holder.network = network
        holder.isAvailable = false
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        holder.networkCapabilities = networkCapabilities
    }

    override fun onLost(network: Network) {
        holder.network = network
        holder.isAvailable = false
    }

    override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
        holder.linkProperties = linkProperties
    }
}