package com.example.modul4.adapter



    import android.net.Uri
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ImageView
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.example.modul4.R
    import com.example.modul4.room.PeopleEntity
    import com.google.android.material.imageview.ShapeableImageView
    import com.google.android.material.textview.MaterialTextView

    class PeopleAdapterRoom(private var playerList: List<PeopleEntity>) :
        RecyclerView.Adapter<PeopleAdapterRoom.PeopleViewHolder>() {

        // Deklarasi variabel untuk callback ketika item diklik
        private lateinit var onItemClickCallback: OnItemClickCallback

        // Fungsi untuk mengatur callback ketika item diklik
        fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
            this.onItemClickCallback = onItemClickCallback
        }

        // Interface untuk callback ketika item diklik
        interface OnItemClickCallback {
            fun onItemClicked(data: PeopleEntity)
        }

        // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
        class PeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val playerName: MaterialTextView = itemView.findViewById(R.id.player_name)
            val playerDescription: MaterialTextView = itemView.findViewById(R.id.player_description)
            val playerImage: ShapeableImageView = itemView.findViewById(R.id.player_image)
            val loveImageView: ImageView = itemView.findViewById(R.id.imageView2)
            val likeTextView: TextView = itemView.findViewById(R.id.like)
        }

        // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): PeopleViewHolder {
            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.activity_people_room, parent, false)
            return PeopleViewHolder(view)
        }

        // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
        override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
            val data = playerList[position]

            holder.playerName.text = data.name
            holder.playerDescription.text = data.description.shorten(85)

            // Mengatur image
            val uri = Uri.fromFile(data.image)
            holder.playerImage.setImageURI(uri)

            // Mengatur aksi ketika item diklik
            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(playerList[holder.absoluteAdapterPosition]) }

            holder.loveImageView.setOnClickListener {
                var likeCount = holder.likeTextView.text.toString().toInt()
                likeCount++
                holder.likeTextView.text = likeCount.toString()
            }
        }

        // Fungsi untuk mendapatkan jumlah item
        override fun getItemCount(): Int = playerList.size

        // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
        private fun String.shorten(maxLength: Int): String {
            return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
            }
    }
