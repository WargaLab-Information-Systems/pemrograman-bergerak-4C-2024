package com.example.modull4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.modull4.room.kontenDatabase
import com.example.modull4.room.kontenViewModel
import com.example.modull4.room.kontenViewModelFactory
import com.example.modull4.utils.reduceFileImage
import com.example.modull4.utils.uriToFile
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class PostActivity : AppCompatActivity() {

    private var currentImageUri: Uri? = null

    private lateinit var kontenImage: ImageView

    private lateinit var appViewModel: kontenViewModel

    private lateinit var kontenDeskripsi: TextInputEditText

    private lateinit var kontenGambar: TextInputEditText


    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            // Menampilkan ImageView jika gambar berhasil dipilih
            kontenImage.visibility = View.VISIBLE
            // Menyimpan URI gambar yang dipilih
            currentImageUri = firstImage.uri
            // Menampilkan pesan bahwa gambar berhasil dimasukkan
            kontenGambar.setText("Gambar berhasil dimasukkan")

            // Menggunakan library Glide untuk menampilkan gambar yang dipilih
            Glide.with(kontenImage)
                .load(firstImage.uri)
                .into(kontenImage)
        } else {
            // Menyembunyikan ImageView jika tidak ada gambar yang dipilih
            View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val factory = kontenViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[kontenViewModel::class.java]

        kontenImage = findViewById(R.id.up_gambar)
        kontenDeskripsi = findViewById(R.id.unggah_deskripsi)
        kontenGambar = findViewById(R.id.unggah_foto)

        onClick()
    }

    private fun onClick(){
        val openImagePicker = findViewById<TextInputEditText>(R.id.unggah_foto)
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
        val btnSavedPlayer = findViewById<MaterialButton>(R.id.tombol_posting)
        btnSavedPlayer.setOnClickListener {
            // Memvalidasi input dan menyimpan data jika valid
            if (validateInput()) {
                savedData()
            }
        }

        val btnBack = findViewById<ImageButton>(R.id.back_button)
        btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun validateInput(): Boolean {
        var error = 0

        // Memeriksa apakah nama pemain kosong
        if (kontenDeskripsi.text.toString().isEmpty()) {
            error++
            kontenDeskripsi.error = "Nama pemain tidak boleh kosong"
        }


        // Memeriksa apakah gambar pemain kosong
        if (kontenGambar.text.toString().isEmpty()) {
            error++
            kontenGambar.error = "Gambar tidak boleh kosong"
        }

        // Mengembalikan true jika tidak ada error, false jika ada error
        return error == 0

    }

    private fun savedData() {
        // Mengubah URI gambar menjadi file dan mengurangi ukuran file
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        // Membuat objek pemain dengan data yang diinputkan
        val player = imageFile?.let {
            kontenDatabase(
                id = 0,
                deskripsi = kontenDeskripsi.text.toString(),
                image = imageFile
            )
        }

        // Menyimpan data pemain ke database
        if (player != null) appViewModel.insertKonten(player)

        // Menampilkan pesan bahwa data pemain berhasil ditambahkan
        Toast.makeText(
            this@PostActivity,
            "Konten berhasil ditambahkan",
            Toast.LENGTH_SHORT
        ).show()

        // Mengakhiri activity
        finish()
    }
}