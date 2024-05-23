class Produk(val nama: String, val harga: Int)

class TokoNatal(val nama: String) {
    val daftarProduk = mutableListOf<Produk>()

    fun tambahProduk(produk: Produk) {
        daftarProduk.add(produk)
    }

    fun tampilkanProduk() {
        println("Daftar Produk di Toko $nama")
        daftarProduk.forEachIndexed { index, produk ->
            println("${index + 1}. ${produk.nama} - Rp ${produk.harga}")
        }
    }
}

fun pesanProduk(toko: TokoNatal, nomorProduk: Int, jumlah: Int) {
    val produkTerpilih = toko.daftarProduk.getOrNull(nomorProduk - 1)
    if (produkTerpilih != null) {
        val totalHarga = produkTerpilih.harga * jumlah
        println("Anda telah memesan $jumlah ${produkTerpilih.nama}")
        println("Total harga: Rp $totalHarga")
    } else {
        println("Produk tidak tersedia")
    }
}
fun main() {
    var lanjut : String
    do {
        // Inisialisasi toko
        val tokoNatal = TokoNatal("Toko Natal Bahagia")

        // Menambahkan beberapa produk
        tokoNatal.tambahProduk(Produk("Pohon Natal", 50000))
        tokoNatal.tambahProduk(Produk("Topi Santa", 20000))
        tokoNatal.tambahProduk(Produk("Kaos Natal", 30000))

        // Menampilkan produk yang tersedia
        tokoNatal.tampilkanProduk()

        // Memesan produk
        val nomorProduk = 2 // Contoh: Memesan Topi Santa
        val jumlah = 2 // Contoh: Memesan 2 buah
        pesanProduk(tokoNatal, nomorProduk, jumlah)

        do{
            println("apakah ingin memesan lagi? (ya / tidak) ")
            lanjut = readLine()!!.toLowerCase()
            if(lanjut != "ya" && lanjut != "tidak"){
                println("mohon masukkan yang sesuai dengan perintah!!")
            } 
        }while(lanjut != "ya" && lanjut != "tidak")
    } while (lanjut == "ya")
}

