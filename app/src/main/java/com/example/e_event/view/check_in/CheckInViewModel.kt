package com.example.e_event.view.check_in

import androidx.lifecycle.*
import com.example.databindingtest.util.Resource
import com.example.databindingtest.util.Status
import com.example.e_event.dispatcher.IAppDispatchers
import com.example.e_event.model.CheckIn
import com.example.e_event.model.User
import com.example.e_event.network.service.backend.IEventService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CheckInViewModel(
    private val service: IEventService,
    private val dispatchers: IAppDispatchers
) : ViewModel() {

    private val checkInResource: MutableLiveData<Resource<CheckIn>> =
        MutableLiveData<Resource<CheckIn>>()

    val checkIn: LiveData<CheckIn> = Transformations.map(checkInResource) {
        return@map it.data
    }

    val name: MutableLiveData<String> = MutableLiveData<String>()
    val email: MutableLiveData<String> = MutableLiveData<String>()

    val isError: LiveData<String> = Transformations.map(checkInResource) {
        return@map it?.status.toString()
    }

    val isLoading: LiveData<Boolean> = Transformations.map(checkInResource) {
        return@map it.status == Status.LOADING
    }

    fun setCheckIn(checkIn: User) {
        viewModelScope.launch(dispatchers.io) {
            service.setCheckIn(checkIn).collect {
                checkInResource.postValue(it)
            }
        }
    }
}