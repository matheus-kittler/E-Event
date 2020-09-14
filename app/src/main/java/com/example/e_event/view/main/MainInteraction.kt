package com.example.e_event.view.main

import com.example.e_event.model.Event

interface MainInteraction {

    fun onLoadEvent(event: Event)
    fun onError(msg: String)

}