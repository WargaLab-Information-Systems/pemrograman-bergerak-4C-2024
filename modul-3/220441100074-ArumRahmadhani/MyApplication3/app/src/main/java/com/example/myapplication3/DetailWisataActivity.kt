package com.example.myapplication3

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView


class DetailWisataActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_wisata)

        // Mengambil data nama, deskripsi, dan gambar dari intent
        val getDataName = intent.getStringExtra("name")
        val getDataDescription = intent.getStringExtra("description")
        val getDataImage = intent.getIntExtra("image", 0)

        // Menghubungkan variabel dengan komponen di layout
        val wisataName = findViewById<MaterialTextView>(R.id.wisata_name)
        val wisataDescription = findViewById<MaterialTextView>(R.id.wisata_description)
        val wisataImage = findViewById<ShapeableImageView>(R.id.wisata_image)

        // Menampilkan data pemain
        wisataName.text = getDataName
        wisataDescription.text = getDataDescription
        wisataImage.setImageResource(getDataImage)

        // Mendapatkan referensi ke tombol bagikan
        val btnShare = findViewById<ImageView>(R.id.bagikan_btn)

        // Menetapkan aksi ketika tombol bagikan diklik
        btnShare.setOnClickListener {

            // Membuat intent untuk berbagi teks
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Wisata Name: $getDataName")
                type = "text/plain"
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
}