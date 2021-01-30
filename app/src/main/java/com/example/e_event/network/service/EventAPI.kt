package com.example.e_event.network.service

import com.example.e_event.model.CheckIn
import com.example.e_event.model.Event
import com.example.e_event.model.People
import retrofit2.Call
import retrofit2.http.*

interface EventAPI {

    @GET("events")
    fun getEvent() : Call<List<Event>>

    @GET("events/{id}")
    fun getDetailsEvent(
        @Path("id") zipId: Int
    ) :Call<Event>

    @POST("checkin")
    fun checkInEvent(@Body checkIn: CheckIn) : Call<CheckIn>
}