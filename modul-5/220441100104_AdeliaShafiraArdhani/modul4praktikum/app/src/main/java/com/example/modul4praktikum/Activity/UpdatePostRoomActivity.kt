package com.example.modul4praktikum.Activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.modul4praktikum.MainActivity
import com.example.modul4praktikum.room.AppViewModel
import com.example.modul4praktikum.room.PostDatabase
import com.example.modul4praktikum.room.RoomViewModelFactory
import com.example.modul4praktikum.utils.reduceFileImage
import com.example.modul4praktikum.utils.uriToFile
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.modul4praktikum.R
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class UpdatePostRoomActivity : AppCompatActivity() {
    // Mendeklarasikan variabel untuk menyimpan URI gambar saat ini dan foto lama.
    private var currentImageUri: Uri? = null
    private var oldPhoto: File? = null
    private lateinit var getDataPost: PostDatabase
    private lateinit var postImage: ImageView
    private lateinit var appViewModel: AppViewModel
    private lateinit var name: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var description: TextInputEditText
    private lateinit var playerImageInput: ImageView

    // Mendeklarasikan imagePickerLauncher untuk memilih gambar dari galeri atau kamera.
    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            postImage.visibility = View.VISIBLE
            currentImageUri = firstImage.uri

            // Menggunakan Glide untuk memuat gambar ke ImageView.
            Glide.with(postImage)
                .load(firstImage.uri)
                .into(postImage)
        } else {
            postImage.visibility = View.GONE
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_post)

        getDataPost = intent.getParcelableExtra("player")!!
        val factory = RoomViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]

        postImage = findViewById(R.id.imagepost)
        name = findViewById(R.id.etName)
        username = findViewById(R.id.etUsername)
        description = findViewById(R.id.etCaption)
        playerImageInput = findViewById(R.id.imagepost)

        name.setText(getDataPost!!.name)
        username.setText(getDataPost!!.username)
        description.setText(getDataPost!!.description)
        Glide.with(this)
            .load(getDataPost.image)
            .into(postImage)

        oldPhoto = getDataPost.image

        onClick()
    }

    private fun onClick() {
        val btnSavedPlayer = findViewById<Button>(R.id.btnUpdate)
        btnSavedPlayer.setOnClickListener {
            if (validateInput()) {
                savedData()
            }
        }
        val openImagePicker = findViewById<TextView>(R.id.btnChangePhoto)
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
        if (name.text.toString().isEmpty()) {
            name.error = "Nama tidak boleh kosong"
            return false
        }
        if (username.text.toString().isEmpty()) {
            username.error = "Username tidak boleh kosong"
            return false
        }
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
    private fun savedData () {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }
        val TanggalUnggah = LocalDate.now()
        val jam = LocalTime.now().withSecond(0).withNano(0) // Menghilangkan detik dan milidetik

        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val waktuUnggah = "$TanggalUnggah ${jam.format(formatter)}"
        val post = imageFile?.let {
            PostDatabase(
                id = getDataPost.id,
                name = name.text.toString(),
                username = "@"+username.text.toString(),
                description = description.text.toString(),
                waktu = "Diubah Pada "+waktuUnggah+" WIB",
                image = it
            )
        }
        Log.d("post", post.toString())

        if (post != null) appViewModel.updatePost(post)
        // Menampilkan pesan bahwa data pemain berhasil diubah.
        Toast.makeText(
            this@UpdatePostRoomActivity,
            "Data post berhasil diubah",
            Toast.LENGTH_SHORT
        ).show()
        // Menutup activity.
        finish()

    }
}
