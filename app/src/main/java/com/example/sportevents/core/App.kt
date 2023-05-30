package com.example.sportevents.core

import android.app.Application
import android.content.Context
import com.example.sportevents.network.NetworkManager

class App : Application() {
    lateinit var networkManager: NetworkManager

    override fun onCreate() {
        super.onCreate()
        networkManager = NetworkManager()
    }

    companion object{
        fun getInstance(context: Context) : App = context.applicationContext as App
    }
}