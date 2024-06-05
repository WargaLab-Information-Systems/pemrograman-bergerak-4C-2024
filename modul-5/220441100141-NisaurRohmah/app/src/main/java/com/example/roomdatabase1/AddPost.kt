package com.example.roomdatabase1

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
import com.example.roomdatabase1.room.AppViewModel
import com.example.roomdatabase1.room.PostDatabase
import com.example.roomdatabase1.room.RoomViewModelFactory
import com.example.roomdatabase1.utils.reduceFileImage
import com.example.roomdatabase1.utils.uriToFile
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class AddPost : AppCompatActivity(){
    private var currentImageUri: Uri? = null
    private lateinit var postViewModel: AppViewModel
    private lateinit var vPostDesc: TextInputEditText
    private lateinit var vPostImage: ImageView
    private lateinit var vText_img: TextView

    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            vPostImage.visibility = View.VISIBLE
            currentImageUri = firstImage.uri
            vText_img.setText("change")
            Glide.with(vPostImage)
                .load(firstImage.uri)
                .into(vPostImage)
        } else {
            View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_post)
        val factory = RoomViewModelFactory.getInstance(this) //ini
        postViewModel = ViewModelProvider(this, factory)[AppViewModel::class.java] //ini

        vPostImage = findViewById(R.id.imagepost)
        vPostDesc = findViewById(R.id.caption)
        vText_img = findViewById(R.id.btnChange)
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
                    imageTitle = "Click to choice the image"
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

        if (vPostDesc.text.toString().isEmpty()) {
            error++
            vPostDesc.error = "Desc is not empty!"
        }
        if (vText_img.text.toString() == "add") {
            error++
            vText_img.error = "Image is not Empty!"
        }

        return error == 0
    }

    private fun savedData() {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        val post = imageFile?.let {
            val descriptionText = vPostDesc.text.toString()
            val words = descriptionText.split(" ")
            val firstTwoWords = words.take(2).joinToString(" ")
            PostDatabase(
                id = 0,
                name = firstTwoWords,
                description = descriptionText,
                image = imageFile,
                like = Random.nextInt(1, 51)
            )
        }

        if (post != null) postViewModel.insertPost(post)

        Toast.makeText(
            this@AddPost,
            "Data Success Added",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }

    fun toMain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}