package com.example.e_event.model

import android.os.Parcel
import android.os.Parcelable
import android.provider.Settings.System.DATE_FORMAT
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Suppress("UNREACHABLE_CODE")
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
) : Parcelable {

    fun getPriceEvent() : String {
        val value = price
        return value.toString().replace(".", ",").replaceAfter("0", "0")
    }

    fun getDateEvent() : String {
        val value = date
        return String.format(DATE_FORMAT, Locale.US, value)
    }

    constructor(parcel: Parcel) : this(
        TODO("people"),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(date)
        parcel.writeString(description)
        parcel.writeString(image)
        parcel.writeValue(longitude)
        parcel.writeValue(latitude)
        parcel.writeValue(price)
        parcel.writeString(title)
        parcel.writeValue(id)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object {
        @JvmField val CREATOR : Parcelable.Creator<Event> = object : Parcelable.Creator<Event> {
            override fun createFromParcel(source: Parcel?): Event? = source?.let { Event(it) }
            override fun newArray(size: Int): Array<Event?> = arrayOfNulls(size)
        }
    }
}