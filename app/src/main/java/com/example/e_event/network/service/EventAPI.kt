package com.example.e_event.network.service

import com.example.e_event.model.Event
import com.example.e_event.model.People
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventAPI {

    @GET("events")
    fun getEvent() : Call<List<Event>>

    @GET("events/{id}")
    fun getDetailsEvent(
        @Path("id") zipId: Int
    ) :Call<Event>

    @POST("events/checkin")
    fun checkInEvent(
        @Body id: Int,
        @Body name: String,
        @Body email: String
    ) : Call<People>
}