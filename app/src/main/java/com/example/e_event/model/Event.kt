package com.example.e_event.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Event : Serializable {

    @SerializedName("people")
    var people: List<People> = listOf()
    @SerializedName("date")
    var date: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("image")
    var image: String? = null
    @SerializedName("longitude")
    var longitude: String? = null
    @SerializedName("latitude")
    var latitude: String? = null
    @SerializedName("price")
    var price: Double? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("id")
    var id: Int? = null

    constructor(people: List<People>?,
                date: String?,
                description: String?,
                image: String?,
                longitude: String?,
                latitude: String?,
                price: Double?,
                title: String?,
                id: Int?) {

        if (people != null) {
            this.people = people
        }
        this.date = date
        this.description = description
        this.image = image
        this.longitude = longitude
        this.latitude = latitude
        this.price = price
        this.title = title
        this.id = id
    }
}