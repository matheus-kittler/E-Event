package com.example.e_event.network.service

import com.example.databindingtest.util.ApiResponse
import com.example.e_event.model.CheckIn
import com.example.e_event.model.Event
import kotlinx.coroutines.Deferred
import mezzari.torres.lucas.network.annotation.Route
import retrofit2.Call
import retrofit2.http.*

interface IEventAPI {

    @GET("events")
    fun loadEvents(): Deferred<ApiResponse<List<Event>>>

    @GET("events/{id}")
    fun getDetailsEvent(
        @Path("id") zipId: Int
    ): Call<Event>

    @POST("checkin")
    fun checkInEvent(@Body checkIn: CheckIn): Call<CheckIn>
}