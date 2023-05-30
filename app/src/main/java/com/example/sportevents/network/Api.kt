package com.example.sportevents.network

import okhttp3.ResponseBody
import retrofit2.http.GET

interface Api {

    @GET("api/v1/demo")
    suspend fun loadData() : ResponseBody
}