package com.example.modul4praktikum

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.features.registerImagePicker
import com.example.modul4praktikum.room.AppViewModel
import com.example.modul4praktikum.room.PostDatabase
import com.example.modul4praktikum.room.RoomViewModelFactory
import com.example.modul4praktikum.utils.reduceFileImage
import com.example.modul4praktikum.utils.uriToFile
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.modul4praktikum.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AddPost : AppCompatActivity(){
    private var currentImageUri: Uri? = null
    // Mendeklarasikan ImageView untuk menampilkan gambar yang dipilih
    private lateinit var playerImage: ImageView
    // Mendeklarasikan ViewModel untuk interaksi dengan database *1
    private lateinit var appViewModel: AppViewModel
    // Mendeklarasikan EditText untuk input nama pemain
    private lateinit var playerName: TextInputEditText
    // Mendeklarasikan EditText untuk input deskripsi pemain
    private lateinit var playerDescription: TextInputEditText
    // Mendeklarasikan EditText untuk input gambar pemain
    private lateinit var playerImageInput: ImageView
    private lateinit var playerUsername: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.posting)
        // Mendapatkan instance ViewModel
        val factory = RoomViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]

        // Menghubungkan variabel dengan komponen di layout
        playerImage = findViewById(R.id.imagepost)
        playerName = findViewById(R.id.etName)
        playerUsername = findViewById(R.id.etUsername)
        playerDescription = findViewById(R.id.etCaption)
        //playerImageInput = findViewById(R.id.player_image_edit)

        // Memanggil fungsi onClick untuk menangani aksi klik
        onClick()

        val back = findViewById<ImageButton>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun onClick(){
        val openImagePicker = findViewById<MaterialTextView>(R.id.btnChangePhoto)
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
        // Menangani aksi klik pada tombol simpan
        val btnSavedPlayer = findViewById<MaterialButton>(R.id.post)
        btnSavedPlayer.setOnClickListener {
            // Memvalidasi input dan menyimpan data jika valid
            if (validateInput()) {
                savedData()
            }
        }

    }
    private fun validateInput(): Boolean {
        var error = 0

        if (playerName.text.toString().isEmpty()) {
            error++
            playerName.error = "Nama tidak boleh kosong"
        }
        if (playerUsername.text.toString().isEmpty()) {
            error++
            playerUsername.error = "Username tidak boleh kosong"
        }
        if (playerDescription.text.toString().isEmpty()) {
            error++
            playerDescription.error = "Deskripsi tidak boleh kosong"
        }
        if (currentImageUri == null) {
            error++
            Toast.makeText(this, "Gambar tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }

        return error == 0
    }

    // Mendeklarasikan image picker untuk memilih gambar dari galeri
    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            // Menampilkan ImageView jika gambar berhasil dipilih
            playerImage.visibility = View.VISIBLE
            // Menyimpan URI gambar yang dipilih
            currentImageUri = firstImage.uri
            // Menampilkan pesan bahwa gambar berhasil dimasukkan
            val cekgambar = findViewById<MaterialTextView>(R.id.btnChangePhoto)
            cekgambar.setText("Ganti Foto")
           // playerImageInput.setText("Gambar berhasil dimasukkan")


            // Menggunakan library Glide untuk menampilkan gambar yang dipilih
            Glide.with(playerImage)
                .load(firstImage.uri)
                .into(playerImage)
        } else {
            // Menyembunyikan ImageView jika tidak ada gambar yang dipilih
            View.GONE
        }
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun savedData() {
        // Mengubah URI gambar menjadi file dan mengurangi ukuran file
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }
        val TanggalUnggah = LocalDate.now()
        val jam = LocalTime.now().withSecond(0).withNano(0) // Menghilangkan detik dan milidetik

        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val waktuUnggah = "$TanggalUnggah ${jam.format(formatter)}"

        // Membuat objek pemain dengan data yang diinputkan
        val player = imageFile?.let {
            PostDatabase(
                name = playerName.text.toString(),
                username = "@"+playerUsername.text.toString(),
                description = playerDescription.text.toString(),
                likes = 0,
                waktu = waktuUnggah,
                image = imageFile
            )
        }

        // Menyimpan data pemain ke database
        if (player != null) appViewModel.insertPost(post = player)

        // Menampilkan pesan bahwa data pemain berhasil ditambahkan
        Toast.makeText(
            this@AddPost,
            "Postingan Berhasil Diunggah",
            Toast.LENGTH_SHORT
        ).show()

        // Mengakhiri activity
        finish()
    }
}