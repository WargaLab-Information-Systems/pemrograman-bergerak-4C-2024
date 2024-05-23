package com.example.modul4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modul4.adapter.Adapter
import com.example.modul4.room.PostDatabase
import com.example.modul4.room.PostViewModel
import com.example.modul4.room.PostViewModelFactory

class BerandaActivity : AppCompatActivity() {

    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapterRoom: Adapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        val factory = PostViewModelFactory.getInstance(this)
        postViewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]
        recyclerView = findViewById(R.id.rv_post)
        recyclerView.layoutManager = LinearLayoutManager(this)

        postViewModel.getAllPost().observe(this) { postData ->
            if (postData != null) {
                postAdapterRoom = Adapter(postData) //ini
                recyclerView.adapter = postAdapterRoom

                postAdapterRoom.setOnItemClickCallback(object :
                    Adapter.OnItemClickCallback {
                    override fun onItemClicked(data: PostDatabase) {
                        showDeleteConfirmationDialog(data)
                    }
                })
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        postViewModel.getAllPost()
    }

    fun toAddPost(view: View) {
        val intent = Intent(this, AddActivity::class.java)
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
}