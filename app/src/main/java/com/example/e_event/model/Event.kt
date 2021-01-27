package com.example.e_event.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Event(
    people: List<People>?,
    @SerializedName("date") var date: Long?,
    @SerializedName("description") var description: String?,
    @SerializedName("image") var image: String?,
    @SerializedName("longitude") var longitude: Double?,
    @SerializedName("latitude") var latitude: Double?,
    @SerializedName("price") var price: Double?,
    @SerializedName("title") var title: String?,
    @SerializedName("id") var id: Int?
) : Serializable {

    @SerializedName("people")
    var people: List<People> = listOf()

    init {
        if (people != null) {
            this.people = people
        }
    }
}