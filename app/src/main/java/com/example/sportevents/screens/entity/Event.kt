package com.example.sportevents.screens.entity



data class Event(
    val players: String,
    var time: Long,
    var isFavorite: Boolean,
) {
    fun getTimeToDate(): Long {
        val currentTimeMillis = System.currentTimeMillis()
        return (time * 1000) - currentTimeMillis
    }
}

