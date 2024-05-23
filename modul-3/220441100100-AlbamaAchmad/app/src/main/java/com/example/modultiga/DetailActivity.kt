package com.example.modultiga

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import java.io.File
import java.io.FileOutputStream

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Mengambil data nama, deskripsi, dan gambar dari intent
        val getDataName = intent.getStringExtra("name")
        val getDataDescription = intent.getStringExtra("description")
        val getDataImage = intent.getIntExtra("image", 0)

        // Menghubungkan variabel dengan komponen di layout
        val playerName = findViewById<MaterialTextView>(R.id.textView13)
        val playerDescription = findViewById<MaterialTextView>(R.id.textView14)
        val playerImage = findViewById<ShapeableImageView>(R.id.imageView5)

        // Menampilkan data pemain
        playerName.text = getDataName
        playerDescription.text = getDataDescription
        playerImage.setImageResource(getDataImage)

        // Mendapatkan referensi ke tombol bagikan
        val btnShare: ImageButton = findViewById<ImageButton>(R.id.imageButton)

        // Menetapkan aksi ketika tombol bagikan diklik
        btnShare.setOnClickListener {
            // Simpan gambar ke cache directory
            val imageUri = saveImageToCache(getDataImage)

            if (imageUri != null) {
                // Membuat intent untuk berbagi gambar dan teks
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, imageUri)
                    putExtra(Intent.EXTRA_TEXT, "$getDataName\n\n$getDataDescription")
                    type = "image/*"
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }

                // Memeriksa apakah WhatsApp terinstal
                val whatsappInstalled = isPackageInstalled("com.whatsapp") || isPackageInstalled("com.whatsapp.w4b")
                Log.d("DetailActivity", "WhatsApp Installed: $whatsappInstalled")
                if (whatsappInstalled) {
                    // Jika WhatsApp terinstal, atur paket intent ke "com.whatsapp" dan mulai activity
                    sendIntent.setPackage("com.whatsapp")
                    startActivity(sendIntent)
                } else {
                    // Jika WhatsApp tidak terinstal, tampilkan pesan toast
                    Toast.makeText(this, "WhatsApp tidak terinstal.", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Tampilkan pesan kesalahan jika URI gambar null
                Toast.makeText(this, "Gagal menyimpan gambar.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Fungsi untuk memeriksa apakah paket tertentu terinstal
    private fun isPackageInstalled(packageName: String): Boolean {
        return try {
            // Mencoba mendapatkan informasi paket
            packageManager.getPackageInfo(packageName, 0)
            Log.d("DetailActivity", "Package $packageName is installed.")
            true
        } catch (e: PackageManager.NameNotFoundException) {
            // Jika paket tidak ditemukan, kembalikan false
            Log.d("DetailActivity", "Package $packageName is NOT installed.")
            false
        }
    }

    // Fungsi untuk menyimpan gambar ke cache directory dan mendapatkan URI
    private fun saveImageToCache(imageResId: Int): Uri? {
        return try {
            val drawable = resources.getDrawable(imageResId, null)
            val bitmap = (drawable as BitmapDrawable).bitmap
            val cachePath = File(cacheDir, "images")
            cachePath.mkdirs()
            val file = File(cachePath, "shared_image.png")
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.close()
            FileProvider.getUriForFile(this, "${applicationContext.packageName}.fileprovider", file)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
