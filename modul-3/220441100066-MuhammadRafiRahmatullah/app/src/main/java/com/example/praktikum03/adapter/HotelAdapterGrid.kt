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

class HotelAdapterGrid(private val hotelList: List<HotelData>) : RecyclerView.Adapter<HotelAdapterGrid.HotelViewHolder>() {

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
        val hotelName: TextView = itemView.findViewById(R.id.hotel_name_grid)
        val hotelImage: ShapeableImageView = itemView.findViewById(R.id.hotel_image_grid)
        val hotelReservation: TextView = itemView.findViewById(R.id.reservation_grid)
        val hotelReview: TextView = itemView.findViewById(R.id.review_grid)
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
        holder.hotelReservation.text = data.reservation
        holder.hotelReview.text = data.review

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