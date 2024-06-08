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


// Kelas PeopleAdapterRoom adalah kelas yang bertugas untuk mengatur tampilan data orang pada RecyclerView.
// Kelas ini menerima daftar orang sebagai parameter konstruktor dan mewarisi RecyclerView.Adapter.
class PeopleAdapterRoom(private var playerList: List<PeopleEntity>) :
    RecyclerView.Adapter<PeopleAdapterRoom.PeopleViewHolder>() {

    // Deklarasi variabel onItemClickCallback yang akan digunakan untuk menangani klik pada item RecyclerView.
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi setOnItemClickCallback digunakan untuk mengatur callback untuk klik item.
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface OnItemClickCallback digunakan untuk mendefinisikan metode callback yang akan dipanggil ketika item diklik.
    interface OnItemClickCallback {
        fun onItemClicked(data: PeopleEntity)
        fun onItemMore(data: PeopleEntity)
    }

    class PeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: MaterialTextView = itemView.findViewById(R.id.player_name)
        val playerDescription: MaterialTextView = itemView.findViewById(R.id.player_description)
        val playerImage: ShapeableImageView = itemView.findViewById(R.id.player_image)
        val loveImageView: ImageView = itemView.findViewById(R.id.imageView2)
        val likeTextView: TextView = itemView.findViewById(R.id.like)
        val btnMore: ImageView = itemView.findViewById(R.id.btn_more)
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
        holder.playerDescription.text = data.description

        // Mengatur image
        val uri = Uri.fromFile(data.image)
        holder.playerImage.setImageURI(uri)

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(playerList[holder.absoluteAdapterPosition]) }

        // Mengatur aksi ketika button more diklik
        holder.btnMore.setOnClickListener { onItemClickCallback.onItemMore(playerList[holder.absoluteAdapterPosition]) }


        // Mengatur aksi ketika like diklik
        holder.loveImageView.setOnClickListener {
            var likeCount = holder.likeTextView.text.toString().toInt()
            likeCount++
            holder.likeTextView.text = likeCount.toString()
        }

    }

    // Fungsi getItemCount digunakan untuk mendapatkan jumlah item pada RecyclerView.
    override fun getItemCount(): Int = playerList.size
}
