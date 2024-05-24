package com.example.myapp.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File


//membuat tabel / entitas didalam database
@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "caption")
    val caption: String,

    @ColumnInfo(name = "image")
    val image: File,

    @ColumnInfo(name = "like")
    var like: Int,

) //Parcelable untuk memudahkan pengiriman objek antar komponen di aplikasi Android

    : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        File(parcel.readString()!!),
        parcel.readInt(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(caption)
        parcel.writeString(image.path)
        parcel.writeInt(like)
    }

    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<UserEntity> {
        override fun createFromParcel(parcel: Parcel): UserEntity {
            return UserEntity(parcel)
        }

        override fun newArray(size: Int): Array<UserEntity?> {
            return arrayOfNulls(size)
        }
    }
}
