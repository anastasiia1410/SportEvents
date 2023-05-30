package com.example.sportevents.screens.entity

data class Sport(
    val shortTitle: String,
    val title: String,
    var listEvent: List<Event>,
    var isExpandEvent: Boolean,
)




