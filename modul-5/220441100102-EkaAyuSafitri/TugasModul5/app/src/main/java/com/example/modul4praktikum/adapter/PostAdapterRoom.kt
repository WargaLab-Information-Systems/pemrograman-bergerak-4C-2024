package com.example.modul4praktikum.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.modul4praktikum.room.PostDatabase
import com.google.android.material.textview.MaterialTextView
import com.modul4praktikum.R

class PostAdapterRoom(private var playerList: List<PostDatabase>) :
    RecyclerView.Adapter<PostAdapterRoom.PlayerViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private var stateFav = false
    private val likedStatus = mutableMapOf<Int, Boolean>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: PostDatabase)
        fun onMoreClicked(data: PostDatabase, position: Int)
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: MaterialTextView = itemView.findViewById(R.id.name)
        val playerUsername: MaterialTextView = itemView.findViewById(R.id.etUsername)
        val playerDescription: MaterialTextView = itemView.findViewById(R.id.description)
        val playerWaktu: MaterialTextView = itemView.findViewById(R.id.waktupost)
        val playerImage: ImageView = itemView.findViewById(R.id.postimage)
        val suka: ImageView = itemView.findViewById(R.id.suka)
        val totalSuka: MaterialTextView = itemView.findViewById(R.id.totalsuka)
        val tagar: MaterialTextView = itemView.findViewById(R.id.tagar)
        val btnMore: ImageView = itemView.findViewById(R.id.btnMore)
        val btnSaved: ImageView = itemView.findViewById(R.id.savedbtn)
        val btnShare: ImageView = itemView.findViewById(R.id.sharebtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.postrv, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val data = playerList[position]

        holder.playerName.text = data.name
        holder.playerUsername.text = data.username
        holder.playerWaktu.text = data.waktu

        val uri = Uri.fromFile(data.image)
        holder.playerImage.setImageURI(uri)

        holder.btnSaved.setOnClickListener {
            stateFav = !stateFav
            holder.btnSaved.setImageResource(if (stateFav) R.drawable.baseline_bookmark_24 else R.drawable.baseline_bookmark_border_24)
        }

        holder.btnShare.setOnClickListener {
            val postData = playerList[position]
            val shareMessage = "Lihat postingan ${postData.name} di aplikasi kami!"
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareMessage)
            }
            val chooserIntent = Intent.createChooser(shareIntent, "Bagikan Postingan Melalui")
            holder.itemView.context.startActivity(chooserIntent)
        }

        val isLiked = likedStatus[position] ?: false
        holder.suka.setImageResource(if (isLiked) R.drawable.baseline_favorite_24 else R.drawable.outline_favorite_border_24)
        holder.totalSuka.text = data.likes.toString()

        holder.btnMore.setOnClickListener {
            onItemClickCallback.onMoreClicked(playerList[holder.layoutPosition], holder.absoluteAdapterPosition)
        }

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(playerList[holder.layoutPosition])
        }

        holder.suka.setOnClickListener {
            val newLikedStatus = !(likedStatus[position] ?: false)
            likedStatus[position] = newLikedStatus

            if (newLikedStatus) {
                holder.suka.setImageResource(R.drawable.baseline_favorite_24)
                data.likes += 1
            } else {
                holder.suka.setImageResource(R.drawable.outline_favorite_border_24)
                data.likes -= 1
            }

            holder.totalSuka.text = data.likes.toString()
        }

        val (cleanDescription, hashtags) = extractDescriptionAndHashtags(data.description)
        holder.playerDescription.text = cleanDescription
        holder.tagar.text = hashtags.joinToString(" ")
    }

    override fun getItemCount(): Int = playerList.size

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
