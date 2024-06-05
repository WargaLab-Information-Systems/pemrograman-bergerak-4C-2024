package com.example.hotel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.R
import com.example.hotel.data.HotelData
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

    class HotelViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val hotelTitle: TextView = itemView.findViewById(R.id.namaHotel)
        val hotelImage: ImageView = itemView.findViewById(R.id.gambarHotel)
        val hotelLocation: TextView = itemView.findViewById(R.id.locasiHotel)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotelViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_rv_horizontal, parent, false)
        return HotelViewHolder(view)
    }

    override fun getItemCount(): Int = hotelList.size

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val data = hotelList[position]

        holder.hotelTitle.text = data.hotelTitle
        holder.hotelImage.setImageResource(data.hotelImage)
        holder.hotelLocation.text = data.hotelLocation

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(hotelList[holder.adapterPosition]) }
    }

    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}