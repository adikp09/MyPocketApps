package com.dev.adi.collectapps.rubik

import android.os.Parcel
import android.os.Parcelable

class Tile (
        val x:Int,
        val y: Int,
        var isEmpty : Boolean,
        var color: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(x)
        parcel.writeInt(y)
        parcel.writeByte(if (isEmpty) 1 else 0)
        parcel.writeString(color)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tile> {
        override fun createFromParcel(parcel: Parcel): Tile {
            return Tile(parcel)
        }

        override fun newArray(size: Int): Array<Tile?> {
            return arrayOfNulls(size)
        }
    }


    override fun toString(): String {
        return "$x, $y, $color"
    }

    override fun equals(other: Any?): Boolean {
        return (x == (other as Tile).x && y == other.y)
    }
}