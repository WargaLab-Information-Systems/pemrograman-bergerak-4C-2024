package com.example.tugasmodul6.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

// Kelas ini digunakan untuk menyimpan data makanan
data class Player(
    // Anotasi ini digunakan untuk menghubungkan variabel dengan field JSON
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("position")
    val position: String,

    @field:SerializedName("goals")
    val goals: Int,

    @field:SerializedName("assists")
    val assists: Int,

    @field:SerializedName("ga")
    val ga: String,

    @field:SerializedName("number")
    val number: Int,

    @field:SerializedName("rating")
    val rating: Float,

    @field:SerializedName("club_logo")
    val club_logo: String,

    @field:SerializedName("player_image")
    val player_image: String,

    @field:SerializedName("background_card")
    val background_card: String
) : Parcelable {
    // Konstruktor ini digunakan untuk membuat objek Player dari Parcel
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

    // Fungsi ini digunakan untuk menulis data Player ke Parcel
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

    // Objek companion yang digunakan untuk membuat objek Player dari Parcel dan array dari Player
    companion object CREATOR : Parcelable.Creator<Player> {
        // Fungsi ini digunakan untuk membuat objek Player dari Parcel
        override fun createFromParcel(source: Parcel): Player {
            return Player(source)
        }

        // Fungsi ini digunakan untuk membuat array dari Player
        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}