package com.example.myapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class NewPostActivity : AppCompatActivity() {


    private var currentImageUri: Uri? = null
    private lateinit var userViewModel: UserViewModel
    private lateinit var caption: TextInputEditText
    private lateinit var imagepost: ImageView
    private lateinit var change: MaterialTextView

    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            imagepost.visibility = View.VISIBLE
            currentImageUri = firstImage.uri
            change.setText("change")
            Glide.with(imagepost)
                .load(firstImage.uri)
                .into(imagepost)
        } else {
            View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_post)
        val factory = UserViewModelFactory.getInstance(this) //ini
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java] //ini

        imagepost = findViewById(R.id.imagepost)
        caption = findViewById(R.id.caption)
        change = findViewById(R.id.btnChange)
        onClick()
    }

    private fun onClick() {
        val openImagePicker = findViewById<ImageView>(R.id.imagepost)
        openImagePicker.setOnClickListener {
            imagePickerLauncher.launch(
                ImagePickerConfig {
                    mode = ImagePickerMode.SINGLE
                    returnMode = ReturnMode.ALL
                    isFolderMode = true
                    folderTitle = "Galeri"
                    isShowCamera = false
                    imageTitle = "Klik untuk memilih gambar"
                    doneButtonText = "Done"
                }
            )
        }

        val btnSavedPlayer = findViewById<Button>(R.id.btnposting)
        btnSavedPlayer.setOnClickListener {
            if (validateInput()) {
                savedData()
            }
        }
    }

    private fun validateInput(): Boolean {
        var error = 0

        if (caption.text.toString().isEmpty()) {
            error++
            caption.error = "Caption tidak boleh kosong!"
        }
        if (change.text.toString() == "add") {
            error++
            change.error = "Gambar tidak boleh kosong!"
        }

        return error == 0
    }

    private fun savedData() {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        val post = imageFile?.let {
            val descriptionText = caption.text.toString()
            UserEntity (
                id = 0,
                caption = descriptionText,
                image = imageFile,
                like = 0
            )
        }

        if (post != null) userViewModel.insertPost(post)

        Toast.makeText(
            this@NewPostActivity,
            "Berhasil upload",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }

    fun toMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}