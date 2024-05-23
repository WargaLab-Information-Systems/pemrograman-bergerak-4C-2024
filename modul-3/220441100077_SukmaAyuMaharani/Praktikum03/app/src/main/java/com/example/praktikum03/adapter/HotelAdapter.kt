package com.example.praktikum03.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikum03.R
import com.example.praktikum03.data.HotelData
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class HotelAdapter(private val hotelList: List<HotelData>) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: HotelData)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class HotelViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val destinationName: TextView = itemView.findViewById(R.id.destination_name)
        val destinationImage: ShapeableImageView = itemView.findViewById(R.id.destination_picture)
        val destinationLocation: TextView = itemView.findViewById(R.id.destination_location)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotelViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel_horizontal, parent, false)
        return HotelViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val data = hotelList[position]

        holder.destinationName.text = data.destinationName
        holder.destinationImage.setImageResource(data.destiantionImage)
        holder.destinationLocation.text = data.destinationLocation
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = hotelList.size

    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}