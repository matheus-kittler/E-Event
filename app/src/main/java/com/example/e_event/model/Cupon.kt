package com.example.e_event.model

import com.google.gson.annotations.SerializedName

class Cupon {

    @SerializedName("discount")
    var discount: Int? = null
    @SerializedName("eventId")
    var eventId: Int? = null
    @SerializedName("id")
    var id: String? = null

    constructor(discount: Int?,
                eventId: Int?,
                id: String?) {

        this.discount = discount
        this.eventId = eventId
        this.id = id
    }
}