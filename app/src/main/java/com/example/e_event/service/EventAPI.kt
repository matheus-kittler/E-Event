package com.example.e_event.service

import com.example.e_event.model.Event
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EventAPI {

    @GET("events")
    fun getEvent() : Call<List<Event>>

    @GET("events/{id}")
    fun getDetailsEvent(
        @Path("id") zipId: Int
    ) :Call<Event>
}