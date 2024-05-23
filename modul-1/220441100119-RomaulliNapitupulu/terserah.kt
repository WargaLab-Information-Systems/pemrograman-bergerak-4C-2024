class Produk(var nama: String, var harga: Int, var stok: Int)

class TokoHijab {
    private val daftarProduk: MutableList<Produk> = mutableListOf()
    private val keranjangBelanja: MutableList<Produk> = mutableListOf()6

    fun tambahProduk(produk: Produk) {
        daftarProduk.add(produk)
    }

    fun lihatDaftarProduk() {
        println("\nDaftar Produk:")
        daftarProduk.forEachIndexed { index, produk ->
            println("${index + 1}. ${produk.nama} - Rp ${produk.harga} - Stok: ${produk.stok}")
        }
    }

    fun beliProduk(nomorProduk: Int): Produk? {
        if (nomorProduk > 0 && nomorProduk <= daftarProduk.size) {
            val produk = daftarProduk[nomorProduk - 1]
            if (produk.stok > 0) {
                produk.stok--
                keranjangBelanja.add(produk)
                println("${produk.nama} telah ditambahkan ke keranjang.")
                return produk
            } else {
                println("Maaf, stok ${produk.nama} habis.")
            }
        } else {
            println("Produk tidak ditemukan.")
        }
        return null
    }

    fun lihatKeranjangBelanja() {
        println("\nKeranjang Belanja Anda:")
        keranjangBelanja.forEachIndexed { index, produk ->
            println("${index + 1}. ${produk.nama} - Rp ${produk.harga}")
        }
    }

    fun hapusProduk(nomorProduk: Int) {
        if (nomorProduk > 0 && nomorProduk <= daftarProduk.size) {
            daftarProduk.removeAt(nomorProduk - 1)
            println("Produk berhasil dihapus.")
        } else {
            println("Produk tidak ditemukan.")
        }
    }

    fun updateProduk(nomorProduk: Int, namaBaru: String, hargaBaru: Int, stokBaru: Int) {
        if (nomorProduk > 0 && nomorProduk <= daftarProduk.size) {
            val produk = daftarProduk[nomorProduk - 1]
            produk.nama = namaBaru
            produk.harga = hargaBaru
            produk.stok = stokBaru
            println("Produk berhasil diperbarui.")
        } else {
            println("Produk tidak ditemukan.")
        }
    }
}

fun main() {
    val toko = TokoHijab()

    val produk1 = Produk("Hijab Segi Empat", 50000, 10)
    val produk2 = Produk("Hijab Pashmina", 75000, 15)
    val produk3 = Produk("Hijab Instan", 60000, 8)
    val produk4 = Produk("Hijab Segi Tiga", 45000, 12)

    toko.tambahProduk(produk1)
    toko.tambahProduk(produk2)
    toko.tambahProduk(produk3)
    toko.tambahProduk(produk4)

    while (true) {
        println("\nSelamat datang di Toko Hijab!")
        println("1. Lihat Daftar Produk")
        println("2. Beli Produk")
        println("3. Lihat Keranjang Belanja")
        println("4. Hapus Produk")
        println("5. Update Produk")
        println("6. Keluar")
        print("Masukkan pilihan Anda: ")

        when (readLine()) {
            "1" -> toko.lihatDaftarProduk()
            "2" -> {
                println("\nDaftar Produk:")
                toko.lihatDaftarProduk()
                print("Masukkan nomor produk yang ingin Anda beli: ")
                val nomorProduk = readLine()?.toIntOrNull() ?: continue
                toko.beliProduk(nomorProduk)
            }
            "3" -> toko.lihatKeranjangBelanja()
            "4" -> {
                println("\nDaftar Produk:")
                toko.lihatDaftarProduk()
                print("Masukkan nomor produk yang ingin Anda hapus: ")
                val nomorProduk = readLine()?.toIntOrNull() ?: continue
                toko.hapusProduk(nomorProduk)
            }
            "5" -> {
                println("\nDaftar Produk:")
                toko.lihatDaftarProduk()
                print("Masukkan nomor produk yang ingin Anda update: ")
                val nomorProduk = readLine()?.toIntOrNull() ?: continue
                print("Masukkan nama baru: ")
                val namaBaru = readLine() ?: continue
                print("Masukkan harga baru: ")
                val hargaBaru = readLine()?.toIntOrNull() ?: continue 
                print("Masukkan stok baru: ")
                val stokBaru = readLine()?.toIntOrNull() ?: continue
                toko.updateProduk(nomorProduk, namaBaru, hargaBaru, stokBaru)
            }
            "6" -> {
                println("Terima kasih telah menggunakan layanan kami!")
                return
            }
            else -> println("Pilihan tidak valid! Silakan masukkan 1, 2, 3, 4, 5, atau 6.")
        }
    }
}


 