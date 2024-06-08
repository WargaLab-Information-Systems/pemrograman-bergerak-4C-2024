package com.example.modul5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modul5.activity.FragmentPopUp
import com.example.modul5.adapter.PostAdapterRoom
import com.example.modul5.room.PostDatabase
import com.example.modul5.room.PostViewModel
import com.example.modul5.room.PostViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapterRoom: PostAdapterRoom
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = PostViewModelFactory.getInstance(this)
        postViewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]
        recyclerView = findViewById(R.id.rv_post)
        recyclerView.layoutManager = LinearLayoutManager(this)

        postViewModel.getAllPost().observe(this) { postData ->
            if (postData != null) {
                postAdapterRoom = PostAdapterRoom(postData) //ini
                recyclerView.adapter = postAdapterRoom

                postAdapterRoom.setOnItemClickCallback(object : PostAdapterRoom.OnItemClickCallback {
                    override fun onItemClicked(data: PostDatabase) {
                        showSelectedPost(data)
                    }

                    override fun onItemMore(data: PostDatabase) {
                        FragmentPopUp(data).show(supportFragmentManager, FragmentPopUp.TAG)
                    }

                })
            }
        }
    }

    private fun showSelectedPost(data: PostDatabase) {
        // Membuat intent untuk berpindah ke DetailPlayerActivity
        val navigateToDetail = Intent(this, DetailPostActivity::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
        navigateToDetail.putExtra("name", data.name)
        navigateToDetail.putExtra("description", data.description)
        navigateToDetail.putExtra("image", data.image)

        // Memulai activity baru
        startActivity(navigateToDetail)
    }

    override fun onRestart() {
        super.onRestart()
        postViewModel.getAllPost()
    }

    fun toAddPost(view: View) {
        val intent = Intent(this, AddPostActivity::class.java)
        startActivity(intent)
    }

    private fun showDeleteConfirmationDialog(post: PostDatabase) {
        AlertDialog.Builder(this)
            .setMessage("Are you sure you want to delete this post?")
            .setPositiveButton("Delete") { _, _ -> deletePost(post) }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deletePost(post: PostDatabase) {
        postViewModel.deletePost(post)
        postAdapterRoom.notifyDataSetChanged()
    }

    private fun updatePost(post: PostDatabase) {
        postViewModel.updatePost(post)
        postAdapterRoom.notifyDataSetChanged()
    }
}