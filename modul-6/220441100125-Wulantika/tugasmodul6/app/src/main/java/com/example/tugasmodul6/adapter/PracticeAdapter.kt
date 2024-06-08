package com.example.tugasmodul6.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.tugasmodul6.R
import com.example.tugasmodul6.data.Player

class PracticeAdapter (private var players: List<Player>) :
    RecyclerView.Adapter<PracticeAdapter.ViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback


    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: Player)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerRating: TextView = itemView.findViewById(R.id.rating)
        val playerNumber: TextView = itemView.findViewById(R.id.number)
        val playerName: TextView = itemView.findViewById(R.id.name)
        val playerPosition: TextView = itemView.findViewById(R.id.position)
        val playerGa: TextView = itemView.findViewById(R.id.ga)
        val logoImage: ImageView = itemView.findViewById(R.id.club_logo)
        val playerImage: ImageView = itemView.findViewById(R.id.player_image)
        val backgroundImage: ImageView = itemView.findViewById(R.id.background_image)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_practice, parent, false)
        return ViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = players[position]

        holder.playerRating.text = data.rating.toString()
        holder.playerNumber.text = data.number.toString()
        holder.playerName.text = data.name
        holder.playerPosition.text = data.position
        holder.playerGa.text = data.ga

        // Mengatur image
        Glide.with(holder.playerImage)
            .load(data.player_image)
            .into(holder.playerImage)

        Glide.with(holder.logoImage)
            .load(data.club_logo)
            .into(holder.logoImage)

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(players[holder.absoluteAdapterPosition]) }

        // Mengatur background image
        Glide.with(holder.backgroundImage.context)
            .load(data.background_card) // replace with your image URL
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    holder.backgroundImage.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // handle cleanup here
                }
            })
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = players.size

    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}