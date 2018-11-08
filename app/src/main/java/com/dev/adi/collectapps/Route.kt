package com.dev.adi.collectapps

import android.os.Parcel
import android.os.Parcelable

class Route (
        val from: String,
        val to: String,
        val weight : Int
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt())

    override fun equals(other: Any?): Boolean {
        return (from == (other as Route).from && to == (other).to)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(from)
        parcel.writeString(to)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Route> {
        override fun createFromParcel(parcel: Parcel): Route {
            return Route(parcel)
        }

        override fun newArray(size: Int): Array<Route?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "$from - $to, $weight"
    }
}