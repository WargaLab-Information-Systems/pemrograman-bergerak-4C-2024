package com.example.modul4praktikum.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File

@Entity
data class PostDatabase(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    val name: String,
    val username: String,
    val description: String,
    val waktu: String,
    var likes:Int = 0,
    val image: File
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        File(parcel.readString()!!)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(description)
        parcel.writeString(waktu)
        parcel.writeInt(likes)
        parcel.writeString(image.path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostDatabase> {
        override fun createFromParcel(parcel: Parcel): PostDatabase {
            return PostDatabase(parcel)
        }

        override fun newArray(size: Int): Array<PostDatabase?> {
            return arrayOfNulls(size)
        }
    }
}
