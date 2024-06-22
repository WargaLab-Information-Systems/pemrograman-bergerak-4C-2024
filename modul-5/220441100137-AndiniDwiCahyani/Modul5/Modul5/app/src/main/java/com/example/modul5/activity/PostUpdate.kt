package com.example.modul5.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.modul5.MainActivity
import com.example.modul5.R
import com.example.modul5.room.AppDatabase
import com.example.modul5.room.PostDatabase
import com.example.modul5.room.PostViewModel
import com.example.modul5.room.PostViewModelFactory
import com.example.modul5.utils.reduceFileImage
import com.example.modul5.utils.uriToFile
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class PostUpdate : AppCompatActivity() {
    private var currentImageUri: Uri? = null
    private var oldPhoto: File? = null
    private lateinit var getDataPost: PostDatabase
    private lateinit var postImage: ImageView
    private lateinit var appViewModel: PostViewModel
    private lateinit var name: TextInputEditText
    private lateinit var description: TextInputEditText
    private lateinit var playerImageInput: ImageView

    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            postImage.visibility = View.VISIBLE
            currentImageUri = firstImage.uri

            Glide.with(postImage)
                .load(firstImage.uri)
                .into(postImage)
        } else {
            postImage.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_update)

        // Inisialisasi ViewModel
        val factory = PostViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]

        // Inisialisasi View
        postImage = findViewById(R.id.post_img_edit)
        name = findViewById(R.id.post_desc_edit) // Pastikan ID ini benar
        description = findViewById(R.id.post_desc_edit)
        playerImageInput = findViewById(R.id.post_img_edit)

        // Ambil data dari Intent
        getDataPost = intent.getParcelableExtra("player")
            ?: run {
                // Tampilkan pesan error atau lakukan tindakan penanganan jika data null
                Toast.makeText(this, "Data post tidak ditemukan", Toast.LENGTH_SHORT).show()
                finish()
                return
            }

        // Isi data ke dalam view
        description.setText(getDataPost.description)
        Glide.with(this)
            .load(getDataPost.image)
            .into(postImage)

        oldPhoto = getDataPost.image

        onClick()
    }

    private fun onClick() {
        val btnSavedPlayer = findViewById<Button>(R.id.btn_savedPost)
        btnSavedPlayer.setOnClickListener {
            if (validateInput()) {
                savedData()
            }
        }
        val openImagePicker = findViewById<TextView>(R.id.text_img)
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
    }

    private fun validateInput(): Boolean {
        if (description.text.toString().isEmpty()) {
            description.error = "Deskripsi tidak boleh kosong"
            return false
        }
        if (currentImageUri == null) {
            Toast.makeText(this, "Gambar tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun savedData() {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }
        val descriptionText = description.text.toString()

        if (descriptionText.isEmpty()) {
            Toast.makeText(this, "Deskripsi tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }
        if (imageFile == null) {
            Toast.makeText(this, "Gambar tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedPost = PostDatabase(
            id = getDataPost.id,
            name = name.text.toString(),
            description = descriptionText,
            image = imageFile
        )

        Log.d("DEBUG", "Updated post: $updatedPost")

        appViewModel.updatePost(updatedPost)

        Toast.makeText(this, "Data post berhasil diubah", Toast.LENGTH_SHORT).show()

        finish()
    }
}

