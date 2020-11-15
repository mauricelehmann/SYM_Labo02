/*
 -----------------------------------------------------------------------------------
 Laboratoire :  02
 Fichier     :  MainApplication.kt
 Auteur(s)   :  Maurice Lehmann
 -----------------------------------------------------------------------------------
 */

package com.heig.labo02

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi

class MainApplication : Application() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate() {
        super.onCreate()
    }
}