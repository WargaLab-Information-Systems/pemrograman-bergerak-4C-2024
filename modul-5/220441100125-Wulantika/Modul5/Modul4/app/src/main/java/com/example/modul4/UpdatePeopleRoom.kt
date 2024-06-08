package com.example.modul4


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
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.example.modul4.R
import com.example.modul4.room.PeopleEntity
import com.example.modul4.room.PeopleViewModel
import com.example.modul4.room.PeopleViewModelFactory
import com.example.modul4.utils.reduceFileImage
import com.example.modul4.utils.uriToFile
import java.io.File

class UpdatePeopleRoom : AppCompatActivity() {

    // Mendeklarasikan variabel untuk menyimpan URI gambar saat ini dan foto lama.
    private var currentImageUri: Uri? = null
    private var oldPhoto: File? = null

    private lateinit var peopleName: TextInputEditText
    private lateinit var peopleImage: TextInputEditText
    private lateinit var peopleImagePlace: ImageView
    private lateinit var getDataPeople: PeopleEntity
    private lateinit var peopleViewModel: PeopleViewModel
    private lateinit var peopleDescription: TextInputEditText

    // Mendeklarasikan imagePickerLauncher untuk memilih gambar dari galeri atau kamera.
    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            peopleImagePlace.visibility = View.VISIBLE
            currentImageUri = firstImage.uri

            // Menggunakan Glide untuk memuat gambar ke ImageView.
            Glide.with(peopleImagePlace)
                .load(firstImage.uri)
                .into(peopleImagePlace)
        } else {
            View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_people_room)

        getDataPeople = intent.getParcelableExtra("people")!!

        val factory = PeopleViewModelFactory.getInstance(this)
        peopleViewModel = ViewModelProvider(this, factory)[PeopleViewModel::class.java]
        peopleImagePlace = findViewById(R.id.player_image)
        peopleName = findViewById(R.id.player_name_edit)
        peopleDescription = findViewById(R.id.player_desc_edit)
        peopleImage = findViewById(R.id.player_image_edit)

        peopleName.setText(getDataPeople!!.name)
        peopleDescription.setText(getDataPeople!!.description)
        Glide.with(this)
            .load(getDataPeople.image)
            .into(peopleImagePlace)

        oldPhoto = getDataPeople.image

        peopleName.setText(getDataPeople!!.name)
        peopleImage.setText("Gambar berhasil ditambahkan")
        Glide.with(this)
            .load(getDataPeople.image)
            .into(peopleImagePlace)

        oldPhoto = getDataPeople.image

        onClick()
    }

    private fun onClick() {
        val savedBtn = findViewById<MaterialButton>(R.id.saved_player)
        savedBtn.setOnClickListener {
            if (validateInput()) {
                savedData()
            }
        }

        // Menangani aksi klik pada TextInputEditText untuk membuka image picker.
        val openImagePicker = findViewById<TextInputEditText>(R.id.player_image_edit)
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

        // Jika input kosong, tambahkan pesan error.
        if (peopleName.text.toString().isEmpty()) {
            error++
            peopleName.error = "Nama pemain tidak boleh kosong"
        }

        if (peopleImage.text.toString().isEmpty()) {
            error++
            peopleImage.error = "Gambar tidak boleh kosong"
        }

        // Jika tidak ada error, kembalikan true. Jika ada, kembalikan false.
        return error == 0
    }

    private fun savedData() {
        // Mengurangi ukuran file gambar.
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        val people = (if (currentImageUri != null) imageFile else oldPhoto)?.let {
            PeopleEntity(
                id = getDataPeople.id,  // Menggunakan ID yang benar di sini.
                name = peopleName.text.toString(),
                description = peopleDescription.text.toString(),
                image = it,
                likeCount = getDataPeople.likeCount
            )
        }

        // Log untuk memastikan data yang akan di-update benar.
        Log.d("UpdatePeopleRoom", "People to update: $people")

        // Memperbarui data pemain di database.
        if (people != null) peopleViewModel.updatePeople(people)

        // Menampilkan pesan bahwa data pemain berhasil diubah.
        Toast.makeText(
            this@UpdatePeopleRoom,
            "Data people berhasil diubah",
            Toast.LENGTH_SHORT
        ).show()

        // Menutup activity.
        finish()
    }
}