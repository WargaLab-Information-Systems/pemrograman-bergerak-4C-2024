package com.avrzll.implementasi6.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.avrzll.implementasi6.R
import com.avrzll.implementasi6.data.remote.PlayerData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class PlayerAdapter(private var players: List<PlayerData>) :
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: PlayerData)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerRating: TextView = itemView.findViewById(R.id.rating)
        val playerNumber: TextView = itemView.findViewById(R.id.number)
        val playerName: TextView = itemView.findViewById(R.id.name)
        val playerPosition: TextView = itemView.findViewById(R.id.position)
        val playerGa: TextView = itemView.findViewById(R.id.ga)
        val playerImage: ImageView = itemView.findViewById(R.id.playerImage)
        val clubLogo: ImageView = itemView.findViewById(R.id.clubLogo)
        val playerBackground: ConstraintLayout = itemView.findViewById(R.id.backgroundCard)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_player, parent, false)
        return ViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = players[position]

        holder.playerRating.text = data.rating.toString()
        holder.playerNumber.text = "#"+data.number.toString()
        holder.playerName.text = data.name
        holder.playerPosition.text = data.position
        holder.playerGa.text = data.ga
        holder.playerImage.loadImage(data.player_image) // Mengatur image player
        holder.clubLogo.loadImage(data.club_logo) // Mengatur Logo Club
        // Background
        Glide.with(holder.playerBackground.context)
            .load(data.background_card) // replace with your image URL
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in
                Drawable>?) {
                    holder.playerBackground.background = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    // handle cleanup here
                }
            })

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(players[holder.absoluteAdapterPosition]) }
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = players.size

    private fun ImageView.loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    this@loadImage.setImageDrawable(resource)
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    // handle cleanup here
                }
            })
    }
}