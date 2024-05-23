package com.example.tugasmodul4

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.tugasmodul4.room.PeopleEntity
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import java.io.ByteArrayOutputStream

class DetailPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        // Mengambil data nama, deskripsi, dan gambar dari intent
        val getDataName = intent.getStringExtra("name")
        val getDataDescription = intent.getStringExtra("description")
        val getDataImage = intent.getIntExtra("image", 0)

        // Mengambil data dari intent yang terdapat di dalam activity Room
        val getDataPlayer = intent.getParcelableExtra<PeopleEntity>("player")

        // Menghubungkan variabel dengan komponen di layout
        val playerName = findViewById<MaterialTextView>(R.id.post_name)
        val playerDescription = findViewById<MaterialTextView>(R.id.post_content)
        val playerImage = findViewById<ShapeableImageView>(R.id.player_image)

        // Menampilkan data pemain
        when {

            // Jika getDataPlayer tidak null, maka akan menampilkan data yang berasal dari getDataPlayer (activity Room)
            getDataPlayer != null -> {
                playerName.text = getDataPlayer.name
                playerDescription.text = getDataPlayer.description
                Glide.with(playerImage)
                    .load(getDataPlayer.image)
                    .into(playerImage)

            }

            // Jika semua kondisi null, maka akan menampilkan data yang berasal dari MainActivity
            else -> {
                playerName.text = getDataName
                playerDescription.text = getDataDescription
                playerImage.setImageResource(getDataImage)
            }
        }

        // Mendapatkan referensi ke tombol bagikan
        val btnShare = findViewById<ImageButton>(R.id.bagikan_btn)

        // Menetapkan aksi ketika tombol bagikan diklik
        btnShare.setOnClickListener {

            // Membuat intent untuk berbagi teks
            val drawable = playerImage.drawable
            if (drawable != null) {
                val bitmap = (drawable as BitmapDrawable).bitmap
                val uri = getImageUri(bitmap)

                // Membuat intent untuk berbagi teks dan gambar
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Nama Postingan: $getDataName\n\nDeskripsi: $getDataDescription")
                    putExtra(Intent.EXTRA_STREAM, uri)
                    type = "image/*"
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }

                // Memeriksa apakah WhatsApp terinstal
                val whatsappInstalled = isPackageInstalled("com.whatsapp") || isPackageInstalled("com.whatsapp.w4b")
                if (whatsappInstalled) {

                    // Jika WhatsApp terinstal, atur paket intent ke "com.whatsapp" dan mulai activity
                    sendIntent.setPackage("com.whatsapp")
                    startActivity(sendIntent)
                } else {

                    // Jika WhatsApp tidak terinstal, tampilkan pesan toast
                    Toast.makeText(this, "WhatsApp tidak terinstal.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Fungsi untuk memeriksa apakah paket tertentu terinstal
    private fun isPackageInstalled(packageName: String): Boolean {
        return try {
            // Mencoba mendapatkan informasi paket
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            // Jika paket tidak ditemukan, kembalikan false
            false
        }
    }

    private fun getImageUri(bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Temp Image", null)
        return Uri.parse(path)
    }
}