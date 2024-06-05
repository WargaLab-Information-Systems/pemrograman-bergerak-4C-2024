package com.example.myapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.room.UserEntity

class UserAdapter (private var postList: List<UserEntity>) :
    RecyclerView.Adapter<UserAdapter.PostViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserEntity)
        fun onItemMore(data: UserEntity)
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val capt: TextView = itemView.findViewById(R.id.description)
        val img: ImageView = itemView.findViewById(R.id.gambar)
        val like: TextView = itemView.findViewById(R.id.jmllike)
        val date: TextView = itemView.findViewById(R.id.waktu)
        val btnLike: ImageView = itemView.findViewById(R.id.like)
        val more: ImageView = itemView.findViewById(R.id.btn_more)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_beranda, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val data = postList[position]

        holder.capt.text = data.caption.shorten(500)
        holder.like.text = data.like.toString()
        holder.more.setOnClickListener { onItemClickCallback.onItemMore(postList[holder.absoluteAdapterPosition]) }

        val uri = Uri.fromFile(data.image)
        holder.img.setImageURI(uri)

        holder.btnLike.setOnClickListener {
            data.like += 1
            holder.like.text = data.like.toString()
        }
    }

    override fun getItemCount(): Int = postList.size

    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}
