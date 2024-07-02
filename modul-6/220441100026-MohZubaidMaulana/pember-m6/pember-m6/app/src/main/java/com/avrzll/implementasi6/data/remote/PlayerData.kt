package com.avrzll.implementasi6.data.remote

import android.os.Parcel
import android.os.Parcelable

data class PlayerData(
    val id: Int,
    val name: String,
    val description: String,
    val position: String,
    val goals: Int,
    val assists: Int,
    val ga: String,
    val number: Int,
    val rating: Float,
    val club_logo: String,
    val player_image: String,
    val background_card: String
) : Parcelable {
    // Konstruktor ini digunakan untuk membuat objek PlayerData dari Parcel
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    // Fungsi ini digunakan untuk menulis PlayerData ke Parcel
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(description)
        dest.writeString(position)
        dest.writeInt(goals)
        dest.writeInt(assists)
        dest.writeString(ga)
        dest.writeInt(number)
        dest.writeFloat(rating)
        dest.writeString(club_logo)
        dest.writeString(player_image)
        dest.writeString(background_card)
    }

    // Fungsi ini digunakan untuk mendeskripsikan jenis konten khusus yang ditangani oleh Parcelable
    override fun describeContents(): Int {
        return 0
    }

    // Objek companion yang digunakan untuk membuat objek PlayerData dari Parcel dan array dari Food
    companion object CREATOR : Parcelable.Creator<PlayerData> {
        // Fungsi ini digunakan untuk membuat objek PlayerData dari Parcel
        override fun createFromParcel(source: Parcel): PlayerData {
            return PlayerData(source)
        }

        // Fungsi ini digunakan untuk membuat array dari PlayerData
        override fun newArray(size: Int): Array<PlayerData?> {
            return arrayOfNulls(size)
        }
    }
}
