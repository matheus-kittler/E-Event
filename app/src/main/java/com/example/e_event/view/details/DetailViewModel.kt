package com.example.e_event.view.details


import android.os.Bundle
import androidx.lifecycle.*
import com.example.databindingtest.util.Resource
import com.example.e_event.dispatcher.IAppDispatchers
import com.example.e_event.model.Event
import com.example.e_event.network.service.backend.IEventService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class DetailViewModel(
    private val service: IEventService,
    private val dispatchers: IAppDispatchers
) : ViewModel() {

    private val eventResource: MutableLiveData<Resource<Event>> = MutableLiveData<Resource<Event>>()

    val event: LiveData<Event> = Transformations.map(eventResource) {
        return@map it.data
    }

    val detail: LiveData<Event> = Transformations.map(eventResource) {
        return@map it.data
    }

    val activityToStart = MutableLiveData<Pair<KClass<*>, Bundle?>>()

//    val detailEvent: LiveData<Event> = MutableLiveData<Event>()

    fun getDetails(id: Int) {
        viewModelScope.launch(dispatchers.io) {
            service.getEvent(id).collect {
                eventResource.postValue(it)
            }
        }
    }
}