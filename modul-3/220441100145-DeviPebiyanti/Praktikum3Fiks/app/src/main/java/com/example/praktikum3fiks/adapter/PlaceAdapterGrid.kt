package com.example.praktikum3fiks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikum3fiks.R
import com.example.praktikum3fiks.data.PlaceData
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class PlaceAdapterGrid(private val playerList: List<PlaceData>) : RecyclerView.Adapter<PlaceAdapterGrid.PlayerViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: PlaceData)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class PlayerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeName: MaterialTextView = itemView.findViewById(R.id.place_name)
        //        val playerDescription: MaterialTextView = itemView.findViewById(R.id.player_description)
        val placeImage: ShapeableImageView = itemView.findViewById(R.id.place_image)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_place_grid, parent, false)
        return PlayerViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val data = playerList[position]

        holder.placeName.text = data.name
//        holder.playerDescription.text = data.description.shorten(85)
        holder.placeImage.setImageResource(data.image)

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(playerList[holder.adapterPosition]) }
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = playerList.size

    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}