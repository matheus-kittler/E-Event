package com.example.e_event.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CheckIn(
    @SerializedName("eventId") var id: Int?,
    @SerializedName("name") var name: String?,
    @SerializedName("email") var email: String?
) : Serializable {

}