package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.adapter.UserAdapter
import com.example.myapp.room.UserEntity
import com.example.myapp.room.UserViewModel
import com.example.myapp.room.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = UserViewModelFactory.getInstance(this)
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        userViewModel.getAllPost().observe(this) { postData ->
            if (postData != null) {
                userAdapter = UserAdapter(postData)
                recyclerView.adapter = userAdapter

                userAdapter.setOnItemClickCallback(object :
                    UserAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: UserEntity) {
                    }
                })
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        userViewModel.getAllPost()
    }

    fun toAddPost(view: View) {
        val intent = Intent(this, NewPostActivity::class.java)
        startActivity(intent)
    }

}