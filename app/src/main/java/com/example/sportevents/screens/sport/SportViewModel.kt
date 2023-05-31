package com.example.sportevents.screens.sport

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sportevents.core.App
import com.example.sportevents.network.NetworkManager
import com.example.sportevents.screens.entity.Event
import com.example.sportevents.screens.entity.Sport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class SportViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val networkManager: NetworkManager
    val sportLD = MutableLiveData<List<Sport>>()

    init {
        networkManager = App.getInstance(application.applicationContext).networkManager
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


    fun loadData() = launch(Dispatchers.IO) {
        val sportList = networkManager.loadData()
        withContext(Dispatchers.Main) {
            sportLD.value = sportList
            startTimer()
        }
    }

    fun changeFavorite(event: Event) {
        event.isFavorite = !event.isFavorite
        sportLD.postValue(sportLD.value)
    }


    fun changeState(sport: Sport) {
        sport.isExpandEvent = !sport.isExpandEvent
        sportLD.postValue(sportLD.value)
    }

    private fun startTimer() = launch(Dispatchers.IO) {
        while (true) {
            delay(1_000)
            withContext(Dispatchers.Main) {
                sportLD.value = sportLD.value
            }
        }
    }
}





