package com.kfc.restorater.model

import android.os.Parcel
import android.os.Parcelable

class RestaurantModel() : Parcelable {
    private var name: String? = null
    private var address: String? = null
    private var phone: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        address = parcel.readString()
        phone = parcel.readString()
    }

    constructor(name: String, address: String, phone: String?) : this() {
        this.name = name
        this.address = address
        this.phone = phone
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RestaurantModel> {
        override fun createFromParcel(parcel: Parcel): RestaurantModel {
            return RestaurantModel(parcel)
        }

        override fun newArray(size: Int): Array<RestaurantModel?> {
            return arrayOfNulls(size)
        }
    }
}