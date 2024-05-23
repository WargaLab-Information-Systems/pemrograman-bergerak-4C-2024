package com.example.praktikum03

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Mengambil data nama, deskripsi, dan gambar dari intent
        val getDataName = intent.getStringExtra("name")
        val getDataHotelName = intent.getStringExtra("hotelname")
        val getDataDescription = intent.getStringExtra("description")
        val getDataImage = intent.getIntExtra("image", 0)

        // Menghubungkan variabel dengan komponen di layout
        val hotelName = findViewById<TextView>(R.id.hotel_name)
        val hotelDesc = findViewById<TextView>(R.id.hotel_description)
        val hotelImage = findViewById<ShapeableImageView>(R.id.hotel_image)

        // Menampilkan data pemain
        hotelName.text = getDataHotelName
        hotelDesc.text = getDataDescription
        hotelImage.setImageResource(getDataImage)

        // Mendapatkan referensi ke tombol bagikan
        val btnShare = findViewById<ImageView>(R.id.bagikan_btn)

        // Mendapatkan referensi ke dashboard
        val btnBack = findViewById<ImageView>(R.id.kembali_btn)

        // Menetapkan aksi ketika tombol bagikan diklik
        btnShare.setOnClickListener {

            // Membuat intent untuk berbagi teks
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Hotel Name: $getDataHotelName")
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

        // Menetapkan aksi ketika tombol back diklik
        btnBack.setOnClickListener {
            // Membuat intent untuk berpindah ke MainActivity
            val intent = Intent(this, DashboardActivity::class.java)

            // Menambahkan dan membawa data username dan password ke intent dengan tujuan ke MainActivity
            intent.putExtra("name", getDataName)

            // Memulai activity baru
            startActivity(intent)
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