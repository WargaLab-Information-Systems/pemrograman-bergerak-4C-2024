package com.example.modull4.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File


@Entity
data class kontenDatabase(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "deskripsi_konten")
    val deskripsi: String,

    @ColumnInfo(name = "gambar_konten")
    val image: File,

    @ColumnInfo(name = "like")
    var like: Int = 0
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        File(parcel.readString()!!),
        parcel.readInt()
    )

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeInt(id)
        p0.writeString(deskripsi)
        p0.writeString(image.path)
        p0.writeInt(like)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<kontenDatabase>{
        override fun createFromParcel(p0: Parcel): kontenDatabase {
            return kontenDatabase(p0)
        }

        override fun newArray(size: Int): Array<kontenDatabase?> {
            return arrayOfNulls(size)
        }
    }
}
