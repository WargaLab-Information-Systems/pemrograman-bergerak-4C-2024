fun main() {
    val toko = Toko("nibina store") 

    toko.tambahProduk("Pisang Coklat", 10, 12000.0)
    toko.tambahProduk("Pisang Keju", 20, 15000.0000000)
    toko.tambahProduk("MIX", 15, 20000.0)

   
    toko.tampilkanDaftarProduk()

   
    beliProduk(toko, "Pisang Coklat", 3)
    beliProduk(toko, "Pisang Keju", 10)
    beliProduk(toko, "MIX", 5)

   
    println("Total Pembelian yang harus Anda Bayar: ${toko.totalPembelian}")
}

class Toko(public val namaToko: String) {
    private val daftarProduk = mutableListOf<Produk>() // 
    var totalPembelian: Double = 0.0 

    fun tambahProduk(nama: String, jumlah: Int, harga: Double) {
        daftarProduk.add(Produk(nama, jumlah, harga))
    }

    fun tampilkanDaftarProduk() {
        println("|-------------------------------------------------------------------------------|")
        println("|                        Daftar Produk di $namaToko:                         |")
        for (produk in daftarProduk) {
            println("              ${produk.nama} | Harga: ${produk.harga} | Jumlah Tersedia: ${produk.jumlah}           ")
        }
        println("|--------------------------------------------------------------------------------|")
    }

    
    fun beliProduk(nama: String, jumlah: Int): Boolean {
        val produk = daftarProduk.find { it.nama == nama } 
        if (produk != null && produk.jumlah >= jumlah) {
            val totalHarga = jumlah * produk.harga
            println("Anda telah membeli $jumlah ${produk.nama} dengan total harga $totalHarga     ")
            produk.jumlah -= jumlah 
            totalPembelian += totalHarga 
            return true
        } else {
            println("Maaf, $nama tidak tersedia atau jumlah yang diminta melebihi stok.")
            return false
        }
    }
}


data class Produk(val nama: String, var jumlah: Int, val harga: Double)


fun beliProduk(toko: Toko, nama: String, jumlah: Int) {
    if (toko.beliProduk(nama, jumlah)) {
        println("Terima kasih telah berbelanja di ${toko.namaToko}")
    } else {
        println("Pembelian produk gagal.")
    }
}
