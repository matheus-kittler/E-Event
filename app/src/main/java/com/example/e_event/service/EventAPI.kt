package com.example.e_event.service

import com.example.e_event.model.Event
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface EventAPI {

    @GET("api/events")
    fun getEvent() : Call<Event>

    @POST("/")
    fun event(
        @Field("date") date: String,
        @Field("description") description: String,
        @Field("image") image: String,
    )
}