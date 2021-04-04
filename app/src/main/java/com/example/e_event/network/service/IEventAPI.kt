package com.example.e_event.network.service

import com.example.databindingtest.util.ApiResponse
import com.example.e_event.model.CheckIn
import com.example.e_event.model.User
import com.example.e_event.model.Event
import kotlinx.coroutines.Deferred
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
        @Body user: User
    ): Deferred<ApiResponse<CheckIn>>
}