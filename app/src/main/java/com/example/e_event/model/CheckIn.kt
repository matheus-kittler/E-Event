package com.example.e_event.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CheckIn() : Serializable {

    @SerializedName("eventId")
    var id: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("email")
    var email: String? = null
}