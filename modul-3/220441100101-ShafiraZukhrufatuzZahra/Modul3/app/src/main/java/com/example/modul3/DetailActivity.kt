package com.example.modul3

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val getDataName = intent.getStringExtra("name")
        val getDataDescription = intent.getStringExtra("description")
        val getDataImage = intent.getIntExtra("image", 0)

        val playerName = findViewById<MaterialTextView>(R.id.player_name)
        val playerDescription = findViewById<MaterialTextView>(R.id.player_description)
        val playerImage = findViewById<ShapeableImageView>(R.id.player_image)

        playerName.text = getDataName
        playerDescription.text = getDataDescription
        playerImage.setImageResource(getDataImage)

        val btnShare = findViewById<ImageButton>(R.id.bagikan_btn)

        btnShare.setOnClickListener {

            // Membuat intent untuk berbagi teks
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Player Name: $getDataName")
                type = "text/plain"
            }

            val whatsappInstalled = isPackageInstalled("com.whatsapp") || isPackageInstalled("com.whatsapp.w4b")
            if (whatsappInstalled) {
                sendIntent.setPackage("com.whatsapp")
                startActivity(sendIntent)
            } else {

                Toast.makeText(this, "WhatsApp tidak terinstal. Instal WhatsApp untuk berbagi informasi.", Toast.LENGTH_LONG).show()

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