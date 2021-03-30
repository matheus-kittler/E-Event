package com.example.e_event.network.service.backend


import com.example.databindingtest.util.NetworkBoundResource
import com.example.databindingtest.util.Resource
import com.example.e_event.model.CheckIn
import com.example.e_event.model.Event
import com.example.e_event.network.service.IEventAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventService(
    private val service: IEventAPI
) : IEventService {

    override suspend fun loadEvents(): Flow<Resource<List<Event>>> {
        return flow {
            NetworkBoundResource(
                collector = this,
                call = service.loadEventsAsync()
            ) {
                it
            }.build()
        }
    }

    override suspend fun getEvent(id: Int): Flow<Resource<Event>> {
        return flow {
            NetworkBoundResource(
                collector = this,
                call = service.getDetailsEventAsync(id)
            ) {
                it
            }.build()
        }
    }

    override suspend fun setCheckIn(name: String, email: String): Flow<Resource<CheckIn>> {
        return flow {
           NetworkBoundResource(
               collector = this,
               call = service.setCheckIn(name, email)
           )  {
               it
           }.build()
        }
    }
}