package com.example.mod4

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.mod4.room.PostEntity
import com.example.mod4.room.PostViewModel
import com.example.mod4.room.PostViewModelFactory
import com.example.mod4.utils.reduceFileImage
import com.example.mod4.utils.uriToFile
import com.google.android.material.textfield.TextInputEditText

class Post_Feed : AppCompatActivity() {
    private var currentImageUri: Uri? = null
    private lateinit var postimg: ImageView
    private lateinit var postitle: TextInputEditText
    private lateinit var postdesc: TextInputEditText
    private lateinit var bImg: Button

    private lateinit var appViewModel: PostViewModel

    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            bImg.visibility = View.VISIBLE
            currentImageUri = firstImage.uri
            Glide.with(postimg)
                .load(firstImage.uri)
                .into(postimg)
        } else {
            postimg.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_feed)
        enableEdgeToEdge()

        val factory = PostViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]

        postimg = findViewById(R.id.postimg)
        postitle = findViewById(R.id.postjudul)
        postdesc = findViewById(R.id.postdesc)
        bImg = findViewById(R.id.bChange)

        onClick()
    }

    private fun onClick() {
        val openImagePicker = findViewById<Button>(R.id.bChange)
        openImagePicker.setOnClickListener {
            imagePickerLauncher.launch(
                ImagePickerConfig {
                    mode = ImagePickerMode.SINGLE
                    returnMode = ReturnMode.ALL
                    isFolderMode = true
                    folderTitle = "Galeri"
                    isShowCamera = false
                    imageTitle = "Tekan untuk memilih gambar"
                    doneButtonText = "Selesai"
                }
            )
        }

        val bPost = findViewById<Button>(R.id.bPost)
        bPost.setOnClickListener {
            if (validateInput()) {
                savedData()
            }
        }
    }

    private fun validateInput(): Boolean {
        var error = 0
        if (postitle.text.toString().isEmpty()) {
            error++
            postitle.error = "Judul tidak boleh kosong"
        }
        if (postdesc.text.toString().isEmpty()) {
            error++
            postdesc.error = "Deskripsi tidak boleh kosong"
        }
        return error == 0
    }

    private fun savedData() {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        val post = imageFile?.let {
            PostEntity(
                id = 0,
                name = postitle.text.toString(),
                description = postdesc.text.toString(),
                image = imageFile,
                likeCount = 0
            )
        }

        if (post != null) appViewModel.insertPost(post)

        Toast.makeText(
            this@Post_Feed,
            "Berhasil meng-upload",
            Toast.LENGTH_SHORT
        ).show()
        finish()
    }

    fun balik(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
