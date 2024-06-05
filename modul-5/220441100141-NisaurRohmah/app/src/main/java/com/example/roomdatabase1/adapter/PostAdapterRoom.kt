package com.example.roomdatabase1.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase1.R
import com.example.roomdatabase1.room.PostDatabase
import com.google.android.material.textview.MaterialTextView

class PostAdapterRoom(private var playerList: List<PostDatabase>) :
    RecyclerView.Adapter<PostAdapterRoom.PlayerViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Map untuk menyimpan status suka dari setiap item
    private val likedStatus = mutableMapOf<Int, Boolean>()

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: PostDatabase)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerDescription: MaterialTextView = itemView.findViewById(R.id.description)
        val playerImage: ImageView = itemView.findViewById(R.id.gambar)
        val suka: ImageView = itemView.findViewById(R.id.like)
        val totalSuka: MaterialTextView = itemView.findViewById(R.id.jmllike)
        val playerWaktu: MaterialTextView = itemView.findViewById(R.id.waktu)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false)
        return PlayerViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val data = playerList[position]

        holder.playerDescription.text = data.description
        holder.totalSuka.text = data.like.toString()

        // Mengatur image
        val uri = Uri.fromFile(data.image)
        holder.playerImage.setImageURI(uri)

        // Set initial like status and icon
        val isLiked = likedStatus[position] ?: false
        holder.suka.setImageResource(if (isLiked) R.drawable.love else R.drawable.outline_love)
        holder.totalSuka.text = data.like.toString()

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(playerList[holder.layoutPosition]) }

        // Mengatur aksi ketika suka ImageView diklik
        holder.suka.setOnClickListener {
            val newLikedStatus = !(likedStatus[position] ?: false)
            likedStatus[position] = newLikedStatus

            if (newLikedStatus) {
                holder.suka.setImageResource(R.drawable.love)
                data.like += 1
            } else {
                holder.suka.setImageResource(R.drawable.outline_love)
                data.like -= 1
            }

            holder.totalSuka.text = data.like.toString()
        }

        // Ekstraksi dan pengaturan hashtags serta deskripsi
        val (cleanDescription, hashtags) = extractDescriptionAndHashtags(data.description)
        holder.playerDescription.text = cleanDescription
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = playerList.size

    // Fungsi untuk mengekstraksi hashtags dari deskripsi dan menghapusnya dari deskripsi
    private fun extractDescriptionAndHashtags(description: String): Pair<String, List<String>> {
        val words = description.split(" ")
        val hashtags = mutableListOf<String>()
        val cleanDescription = StringBuilder()

        for (word in words) {
            if (word.startsWith("#")) {
                hashtags.add(word)
            } else {
                if (cleanDescription.isNotEmpty()) {
                    cleanDescription.append(" ")
                }
                cleanDescription.append(word)
            }
        }

        return Pair(cleanDescription.toString(), hashtags)
    }
}
