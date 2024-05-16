package com.example.hotel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.R
import com.example.hotel.data.HotelData
import com.google.android.material.imageview.ShapeableImageView

class HotelAdapterGrid (private val hotelList: List<HotelData>) : RecyclerView.Adapter<HotelAdapterGrid.HotelViewHolder>() {

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
        val hotelName: TextView = itemView.findViewById(R.id.namaHotelGrid)
        val hotelImage: ShapeableImageView = itemView.findViewById(R.id.gambarHotelGrid)
        val hotelReservation: TextView = itemView.findViewById(R.id.reservasiGrid)
        val hotelReview: TextView = itemView.findViewById(R.id.rating_grid)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotelViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel_grid, parent, false)
        return HotelViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val data = hotelList[position]

        holder.hotelName.text = data.name
        holder.hotelImage.setImageResource(data.image)
        holder.hotelReservation.text = data.reservasi
        holder.hotelReview.text = data.rating

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(hotelList[holder.adapterPosition]) }
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = hotelList.size

    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }

}