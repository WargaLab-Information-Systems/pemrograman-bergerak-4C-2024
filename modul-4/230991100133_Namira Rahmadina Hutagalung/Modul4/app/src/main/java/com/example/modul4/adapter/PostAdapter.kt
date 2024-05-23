package com.example.modul4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modul4.R
import com.example.modul4.data.PostData
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

    // Kelas adapter untuk RecyclerView dengan tampilan LinearLayoutManager
    class PostAdapter(private val playerList: List<PostData>) : RecyclerView.Adapter<PostAdapter.PlayerViewHolder>() {

        // Deklarasi variabel untuk callback ketika item diklik
        private lateinit var onItemClickCallback: OnItemClickCallback

        // Fungsi untuk mengatur callback ketika item diklik
        fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
            this.onItemClickCallback = onItemClickCallback
        }

        // Interface untuk callback ketika item diklik
        interface OnItemClickCallback {
            fun onItemClicked(data: PostData)
        }

        // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
        class PlayerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
            val playerName: ShapeableImageView= itemView.findViewById(R.id.profile)
            val playerDescription: TextView = itemView.findViewById(R.id.at)
            val playerImage: TextView = itemView.findViewById(R.id.at2)
            val playerDek : TextView =itemView.findViewById(R.id.post_desc)
        }

        // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): PlayerViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
            return PlayerViewHolder(view)
        }

        // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
        override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
            val data = playerList[position]

            holder.playerName.setImageResource(data.profile)
            holder.playerDescription.text = data.at.shorten(85)
            holder.playerImage.text = data.at2.shorten(85)
            holder.playerDek.text = data.description.shorten(85)

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
