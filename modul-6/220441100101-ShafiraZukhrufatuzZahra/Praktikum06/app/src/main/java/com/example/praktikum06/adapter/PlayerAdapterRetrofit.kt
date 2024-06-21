package com.example.praktikum06.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.praktikum06.R
import com.example.praktikum06.model.Player
import com.google.android.material.imageview.ShapeableImageView

class PlayerAdapterRetrofit(
    private val playerList: List<Player>,
    private val onItemClickCallback: OnItemClickCallback
) : RecyclerView.Adapter<PlayerAdapterRetrofit.PlayerViewHolder>() {

    interface OnItemClickCallback {
        fun onItemClicked(data: Player)
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.txt_nama)
        val playerPosition: TextView = itemView.findViewById(R.id.txt_position)
        val playerGa: TextView = itemView.findViewById(R.id.txt_ga)
        val playerNumber: TextView = itemView.findViewById(R.id.txt_number)
        val playerRating: TextView = itemView.findViewById(R.id.txt_rating)
        val playerClubLogo: ShapeableImageView = itemView.findViewById(R.id.img_club)
        val playerImage: ImageView = itemView.findViewById(R.id.img_player)
        val backgroundContainer: ConstraintLayout = itemView.findViewById(R.id.img_bg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val data = playerList[position]
        holder.playerName.text = data.name
        holder.playerPosition.text = data.position
        holder.playerGa.text = data.ga
        holder.playerNumber.text = "#"+data.number.toString()
        holder.playerRating.text = data.rating.toString()

        Glide.with(holder.playerClubLogo.context).load(data.clubLogo).into(holder.playerClubLogo)
        Glide.with(holder.playerImage.context).load(data.playerImage).into(holder.playerImage)
        Glide.with(holder.backgroundContainer.context)
            .load(data.backgroundCard)
            .into(object : CustomTarget<Drawable>(){
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    holder.backgroundContainer.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }
            })


        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(playerList[holder.absoluteAdapterPosition]) }
    }

    override fun getItemCount(): Int = playerList.size
}
