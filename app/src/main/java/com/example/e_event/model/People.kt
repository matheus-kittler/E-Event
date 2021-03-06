package com.example.e_event.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class People : Serializable {

    @SerializedName("picture")
    var picture: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("eventId")
    var eventId: Int? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("email")
    var email: String? = null

    constructor(picture: String?, name: String?,
    eventId: Int?, id: Int?, email: String?) {
        this.picture = picture
        this.name = name
        this.eventId = eventId
        this.id = id
        this.email = email
    }
}