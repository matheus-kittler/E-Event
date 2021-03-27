package com.example.e_event.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Event(
    @SerializedName("date") var date: Long?,
    @SerializedName("description") var description: String?,
    @SerializedName("image") var image: String?,
    @SerializedName("longitude") var longitude: Double?,
    @SerializedName("latitude") var latitude: Double?,
    @SerializedName("price") var price: Double?,
    @SerializedName("title") var title: String?,
    @SerializedName("id") var id: Int?
) : Serializable {

    fun getPriceEvent(): String {
        return price.toString().replace(".", ",").replaceAfter("0", "0")
    }
}