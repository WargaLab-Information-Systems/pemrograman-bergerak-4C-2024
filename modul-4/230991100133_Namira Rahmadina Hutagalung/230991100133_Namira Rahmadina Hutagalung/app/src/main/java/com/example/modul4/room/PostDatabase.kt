package com.example.modul4.room

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

    @ColumnInfo(name = "post_title")
    val name: String,

    @ColumnInfo(name = "post_desc")
    val description: String,

    @ColumnInfo(name = "post_image")
    val image: File,

    @ColumnInfo(name = "post_like")
    var like: Int = 0,


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        File(parcel.readString()!!),
        parcel.readInt(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(image.path)
        parcel.writeInt(like)
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