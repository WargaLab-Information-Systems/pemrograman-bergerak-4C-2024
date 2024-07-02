package com.example.modul4.adapter

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.modul4.R
import com.example.modul4.room.PostDatabase
import com.example.modul4.room.PostViewModel
import com.google.android.material.imageview.ShapeableImageView

class PostAdapterRoom(private var postList: List<PostDatabase>, private val postViewModel: PostViewModel) :
    RecyclerView.Adapter<PostAdapterRoom.PostViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

        private var stateFav = false


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onMoreClicked(data: PostDatabase, position: Int)
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postTitle: TextView = itemView.findViewById(R.id.post_title)
        val postDesc: TextView = itemView.findViewById(R.id.post_desc)
        val postImg: ShapeableImageView = itemView.findViewById(R.id.post_img)
        val postLike: TextView = itemView.findViewById(R.id.post_like)
        val postDate: TextView = itemView.findViewById(R.id.post_time)
        val iconLike: ImageView = itemView.findViewById(R.id.icon_like)

        //btn
        val btnLike: LinearLayout = itemView.findViewById(R.id.btn_like)
        val btnMore: ImageView = itemView.findViewById(R.id.btn_more)
        val btnShare: ImageView = itemView.findViewById(R.id.btn_share)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val data = postList[position]

        holder.postTitle.text = data.name
        holder.postDesc.text = data.description.shorten(500)
        holder.postLike.text = data.like.toString()

        val uri = Uri.fromFile(data.image)
        holder.postImg.setImageURI(uri)

        holder.btnLike.setOnClickListener {
            stateFav = !stateFav
            holder.iconLike.setImageResource(if (stateFav) R.drawable.likeactive else R.drawable.loveicon)
            if (stateFav){
                data.like += 1
            }else{
                data.like -= 1
            }
            holder.postLike.text = data.like.toString()
//            //holder.btnLike.setOnClickListener {
//                if (!likedItems.get(position, false)) {
//                    // Item is currently not liked
//                    data.like += 1
//                    likedItems.put(position, true)
//                    holder.iconLike.setImageResource(R.drawable.likeactive)
//                } else {
//                    // Item is currently liked
//                    data.like -= 1
//                    likedItems.put(position, false)
//                    holder.iconLike.setImageResource(R.drawable.loveicon)
//                }
//                holder.postLike.text = data.like.toString()
//                postViewModel.updatePost(data)
            //}
        }
        // Mengatur aksi ketika button more diklik
        holder.btnMore.setOnClickListener {
            onItemClickCallback.onMoreClicked(postList[holder.absoluteAdapterPosition], holder.absoluteAdapterPosition)
        }
 }

    override fun getItemCount(): Int = postList.size

    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }

}
