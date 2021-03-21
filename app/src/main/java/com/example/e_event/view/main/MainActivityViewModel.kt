package com.example.e_event.view.main


import androidx.lifecycle.*
import com.example.databindingtest.dispatcher.IAppDispatchers
import com.example.databindingtest.util.Resource
import com.example.e_event.model.Event
import com.example.e_event.network.service.backend.IEventService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val service: IEventService,
    private val dispatchers: IAppDispatchers
) : ViewModel() {

    private val eventsResource: MutableLiveData<Resource<List<Event>>> = MutableLiveData<Resource<List<Event>>>()

    val events: LiveData<List<Event>> = Transformations.map(eventsResource) {
        return@map it?.data
    }
    val error: MutableLiveData<String> = MutableLiveData<String>()

    var event: Event? = null

    fun loadEvents() {
        viewModelScope.launch(dispatchers.io) {
            service.loadEvents().collect {
                eventsResource.postValue(it)
            }
        }
    }

    fun getEvent() {
        val eventId = event?.id ?: return
    }

}