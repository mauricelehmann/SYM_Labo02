package com.heig.labo02

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.heig.labo02.comm.network.ConnectivityStateHolder.registerConnectivityBroadcaster

class MainApplication : Application() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate() {
        super.onCreate()
        registerConnectivityBroadcaster()
    }
}