//Sukma Ayu Maharani
//220441100077
//Pemrograman Bergerak-4C
// Toko Skincare 

// Deklarasi kelas ProdukSkincare
class ProdukSkincare(val nama: String, val jenis: String, val harga: Int, val stok: Int)

// Fungsi untuk menampilkan informasi produk skincare
fun tampilkanInfoProduk(produk: ProdukSkincare) {
    println("Nama: ${produk.nama}")
    println("Jenis: ${produk.jenis}")
    println("Harga: Rp ${produk.harga}")
    println("Stok: ${produk.stok}")
    println()
}

// Fungsi utama
fun main() {
    // Inisialisasi daftar produk skincare
    val daftarProduk = listOf(
        ProdukSkincare("Cleansing Foam", "Pembersih Wajah", 50000, 10),
        ProdukSkincare("Moisturizing Cream", "Pelembap", 75000, 15),
        ProdukSkincare("Sunscreen", "Tabir Surya", 90000, 8),
        ProdukSkincare("Serum", "Perawatan Wajah", 120000, 12),
        ProdukSkincare("Toner", "Pelembap", 55000, 20)
    )

    // Menampilkan informasi setiap produk
    println("===== DAFTAR PRODUK SKINCARE =====")
    for (produk in daftarProduk) {
        tampilkanInfoProduk(produk)
    }

    // Menampilkan produk dengan stok kurang dari 10
    println("===== PRODUK DENGAN STOK DI BAWAH 10 =====")
    for (produk in daftarProduk) {
        if (produk.stok < 10) {
            tampilkanInfoProduk(produk)
        }
    }

    // Menampilkan produk dengan harga di atas 75000
    println("===== PRODUK DENGAN HARGA DI ATAS Rp75000 =====")
    for (produk in daftarProduk) {
        if (produk.harga > 75000) {
            tampilkanInfoProduk(produk)
        }
    }
}
