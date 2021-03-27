package com.example.e_event.view.details


import androidx.lifecycle.*
import com.example.databindingtest.util.Resource
import com.example.e_event.dispatcher.IAppDispatchers
import com.example.e_event.model.Event
import com.example.e_event.network.service.backend.IEventService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    private val service: IEventService,
    private val dispatchers: IAppDispatchers
) : ViewModel() {

    val eventId: MutableLiveData<Event> = MutableLiveData<Event>()
    private val eventResource: MutableLiveData<Resource<Event>> = MutableLiveData<Resource<Event>>()

    val event: LiveData<Event> = Transformations.map(eventResource) {
        return@map it?.data
    }
//    var event: Event? = null

    fun getDetails(id: Int) {
//        val eventId = event?.id ?: return
        viewModelScope.launch(dispatchers.io) {
            service.getEvent(id).collect {
                eventResource.postValue(it)
            }
        }
    }
}