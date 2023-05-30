package com.example.sportevents.network.entity

import com.example.sportevents.screens.entity.Sport
import com.google.gson.annotations.SerializedName

data class SportNetwork(
    @SerializedName("i")
    val shortTitle: String,
    @SerializedName("d")
    val title: String,
    @SerializedName("e")
    val listEvent: List<EventNetwork>,
)

fun SportNetwork.toSport(): Sport {
    return Sport(
        shortTitle = this.shortTitle,
        title = this.title,
        listEvent = this.listEvent.map { it.toEvent() },
        isExpandEvent = true
    )
}