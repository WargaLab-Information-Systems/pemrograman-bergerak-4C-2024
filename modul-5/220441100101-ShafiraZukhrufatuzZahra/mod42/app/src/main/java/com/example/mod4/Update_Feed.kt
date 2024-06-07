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
import com.google.android.material.textfield.TextInputEditText
import com.example.mod4.room.PostEntity
import com.example.mod4.room.PostViewModel
import com.example.mod4.room.PostViewModelFactory
import com.example.mod4.utils.reduceFileImage
import com.example.mod4.utils.uriToFile
import java.io.File

class Update_Feed : AppCompatActivity() {

    private var currentImageUri: Uri? = null
    private var imglama: File? = null

    private lateinit var updateImg: ImageView
    private lateinit var updatetitle: TextInputEditText
    private lateinit var updatedesc: TextInputEditText
    private lateinit var bImg: Button

    private lateinit var getDataPost: PostEntity
    private lateinit var appViewModel: PostViewModel

    private val imagePickerLauncher = registerImagePicker {
        val firstImage = it.firstOrNull() ?: return@registerImagePicker
        if (firstImage.uri.toString().isNotEmpty()) {
            bImg.visibility = View.VISIBLE
            currentImageUri = firstImage.uri
            Glide.with(updateImg)
                .load(firstImage.uri)
                .into(updateImg)
        } else {
            updateImg.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_feed)
        getDataPost = intent.getParcelableExtra("post")!!

        val factory = PostViewModelFactory.getInstance(this)
        appViewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]

        updateImg = findViewById(R.id.upimg)
        updatetitle = findViewById(R.id.uptitle)
        updatedesc = findViewById(R.id.updesc)
        bImg = findViewById(R.id.bChange)

        updatetitle.setText(getDataPost!!.name)
        updatedesc.setText(getDataPost.description)

        imglama = getDataPost.image
        Glide.with(updateImg)
            .load(getDataPost.image)
            .into(updateImg)
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

        val bUpdate = findViewById<Button>(R.id.bUpdate)
        bUpdate.setOnClickListener {
            if (validateInput()) {
                savedData()
            }
        }
    }

    private fun validateInput(): Boolean {
        var error = 0

        if (updatetitle.text.toString().isEmpty()) {
            error++
            updatetitle.error = "Judul tidak boleh kosong"
        }
        if (updatedesc.text.toString().isEmpty()) {
            error++
            updatedesc.error = "Deskripsi tidak boleh kosong"
        }
        return error == 0
    }

    private fun savedData() {
        val imageFile = currentImageUri?.let { uriToFile(it, this).reduceFileImage() }

        val post = (if (currentImageUri != null) imageFile else imglama)?.let {
            PostEntity(
                id = getDataPost.id,
                name = updatetitle.text.toString(),
                description = updatedesc.text.toString(),
                image = it,
                likeCount = getDataPost.likeCount
            )
        }

        if (post != null) appViewModel.updatePost(post)

        Toast.makeText(
            this@Update_Feed,
            "Postingan berhasil di-update",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }

    fun balik(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}