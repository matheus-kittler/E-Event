package com.example.e_event.view.main


import androidx.lifecycle.*
import com.example.e_event.dispatcher.IAppDispatchers
import com.example.databindingtest.util.Resource
import com.example.databindingtest.util.Status
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
    val isError: LiveData<String> = Transformations.map(eventsResource) {
        if (it.message != null && it.status == Status.ERROR) {
            return@map it?.message
        } else {
            return@map null
        }
    }

    val isLoading: LiveData<Boolean> = Transformations.map(eventsResource) {
        return@map it.status == Status.LOADING
    }



    fun loadEvents() {
        viewModelScope.launch(dispatchers.io) {
            service.loadEvents().collect {
                eventsResource.postValue(it)
            }
        }
    }

//    fun getEvent() {
//        val eventId = event?.id ?: return
//    }

}