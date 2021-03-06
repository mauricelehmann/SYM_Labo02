/*
 -----------------------------------------------------------------------------------
 Laboratoire :  02
 Fichier     :  NetworkMonitorUtil.kt
 Auteur(s)   :  Soulaymane Lamrani
 Remarque    : Légèrement remanié à partir de
               https://github.com/johncodeos-blog/CheckInternetAndroidExample
 -----------------------------------------------------------------------------------
 */

package com.heig.labo02.comm.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

class NetworkMonitorUtil(context: Context) {

    private var mContext = context

    private lateinit var networkCallback: NetworkCallback

    var isAvailable: Boolean = false

    fun register() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // Use NetworkCallback for Android 9 and above
            val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

            if (connectivityManager.activeNetwork == null) {

                // UNAVAILABLE
                isAvailable = false
            }

            // Check when the connection changes
            networkCallback = object : NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)

                    // UNAVAILABLE
                    isAvailable = false
                }

                override fun onCapabilitiesChanged(network: Network,
                                                   networkCapabilities: NetworkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    when {
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {

                            // WIFI
                            isAvailable = true
                        }
                        else -> {
                            // CELLULAR
                            isAvailable = true
                        }
                    }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            // Use Intent Filter for Android 8 and below
            val intentFilter = IntentFilter()
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
            mContext.registerReceiver(networkChangeReceiver, intentFilter)
        }
    }

    fun unregister() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val connectivityManager =
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } else {
            mContext.unregisterReceiver(networkChangeReceiver)
        }
    }

    private val networkChangeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo

            isAvailable = activeNetworkInfo != null
        }
    }
}