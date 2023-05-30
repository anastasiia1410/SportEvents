package com.example.sportevents.network.entity

import com.example.sportevents.screens.entity.Event
import com.google.gson.annotations.SerializedName

data class EventNetwork(
    @SerializedName("d")
    val players: String,
    @SerializedName("tt")
    val time: Long,
)

fun EventNetwork.toEvent() : Event {
    return Event(
        players = this.players,
        time = this.time,
        isFavorite = false
    )
}