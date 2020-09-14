package com.example.e_event.service

import com.example.e_event.model.Event
import retrofit2.Call
import retrofit2.http.GET

interface EventAPI {

    @GET("events")
    fun getEvent() : Call<List<Event>>

}