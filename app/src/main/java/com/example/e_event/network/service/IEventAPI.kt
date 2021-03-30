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
    fun loadEventsAsync(): Deferred<ApiResponse<List<Event>>>

    @GET("events/{id}")
    fun getDetailsEventAsync(
        @Path("id") zipId: Int
    ): Deferred<ApiResponse<Event>>

    @POST("checkin")
    fun setCheckIn(
        @Body eventId: Int, name: String, email: String
    ): Deferred<ApiResponse<String>>
}