package com.example.myapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.myapp.room.UserEntity
import com.example.myapp.room.UserViewModel
import com.example.myapp.room.UserViewModelFactory
import com.example.myapp.utils.reduceFileImage
import com.example.myapp.utils.uriToFile
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import java.io.File

class UpdateUserRoomActivity : AppCompatActivity() {

    // Mendeklarasikan variabel untuk menyimpan URI gambar saat ini dan foto lama.
    private var currentImageUri: Uri? = null
    private var oldPhoto: File? = null

    private lateinit var caption: TextInputEditText
    private lateinit var imagePost: ImageView
    private lateinit var change: MaterialTextView
    private lateinit var getDataUser: UserEntity
    private lateinit var userViewModel: UserViewModel

    // Mendeklarasikan imagePickerLauncher untuk memilih gambar dari galeri atau kamera.
    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            imagePost.visibility = View.VISIBLE
            currentImageUri = firstImage.uri
            change.setText("change")

            // Menggunakan Glide untuk memuat gambar ke ImageView.
            Glide.with(imagePost)
                .load(firstImage.uri)
                .into(imagePost)
        } else {
            View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_room)

        getDataUser = intent.getParcelableExtra("user")!!

        val factory = UserViewModelFactory.getInstance(this)
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        caption = findViewById(R.id.caption)
        imagePost = findViewById(R.id.imagepost)
        change = findViewById(R.id.btnChange)

        caption.setText(getDataUser.caption)
        change.setText("change")
        oldPhoto = getDataUser.image
        Glide.with(this)
            .load(oldPhoto)
            .into(imagePost)



        onClick()
    }

    private fun onClick() {
        val savedBtn = findViewById<MaterialButton>(R.id.btn_update_posting)
        savedBtn.setOnClickListener {
            if (validateInput()) {
                savedData()
            }
        }
        // Menangani aksi klik pada TextInputEditText untuk membuka image picker.
        val openImagePicker = findViewById<MaterialTextView>(R.id.btnChange)
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
        var error = 0

        if (caption.text.toString().isEmpty()) {
            error++
            caption.error = "Caption tidak boleh kosong"
        }

        if (change.text.toString() == "add") {
            error++
            change.error = "Gambar tidak boleh kosong!"
        }

        return error == 0
    }

    private fun savedData() {
        // Mengurangi ukuran file gambar.
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        // Membuat objek PlayerEntity baru dengan data yang diperbarui.
        val user = (if (currentImageUri != null) imageFile else oldPhoto)?.let {
            UserEntity(
                id = getDataUser.id,
                caption = caption.text.toString(),
                image = imageFile ?: oldPhoto!!,
                like = getDataUser.like
            )
        }

        Log.d("user", user.toString())

        // Memperbarui data pemain di database.
        if (user != null) userViewModel.updatePost(user)
        // Menampilkan pesan bahwa data pemain berhasil diubah.
        Toast.makeText(
            this@UpdateUserRoomActivity,
            "Data berhasil diubah",
            Toast.LENGTH_SHORT
        ).show()

        // Menutup activity.
        finish()
    }
}
