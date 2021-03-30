package com.example.e_event.network.service.backend

import com.example.databindingtest.util.Resource
import com.example.e_event.model.CheckIn
import com.example.e_event.model.Event
import kotlinx.coroutines.flow.Flow

interface IEventService {
    suspend fun loadEvents(): Flow<Resource<List<Event>>>

    suspend fun getEvent(id: Int): Flow<Resource<Event>>

    suspend fun setCheckIn(eventId: Int, name: String, email: String): Flow<Resource<String>>
}