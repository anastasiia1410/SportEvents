package com.example.sportevents.network

import com.example.sportevents.network.entity.SportNetwork
import com.example.sportevents.network.entity.toSport
import com.example.sportevents.screens.entity.Sport
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager {
    private val gson: Gson
    private val client: OkHttpClient
    private val retrofit: Retrofit
    private val api: Api

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        gson = GsonBuilder().create()
        retrofit = Retrofit.Builder()
            .baseUrl("https://62a054d8202ceef7086aed64.mockapi.io/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }

    suspend fun loadData(): List<Sport> {
        val responseBody: ResponseBody = api.loadData()
        val json = "{ \"array\" :" + responseBody.string() + " }"
        val jsonArray = JSONObject(json).getJSONArray("array")
        val dataList = mutableListOf<SportNetwork>()
        for (i in 0 until jsonArray.length()) {
            val dataJson = jsonArray.getJSONObject(i)
            val data = gson.fromJson(dataJson.toString(), SportNetwork::class.java)
            dataList.add(data)
        }
        return dataList.map { it.toSport() }.distinctBy { it.title }
    }
}


