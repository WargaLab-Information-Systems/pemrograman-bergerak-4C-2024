import java.util.Scanner

// Deklarasi class Film untuk menyimpan informasi film
class Film(val judul: String, val hargaTiket: Int)

// Deklarasi class Transaksi untuk menyimpan informasi transaksi
class Transaksi(val film: Film, val jumlahTiket: Int) {
    fun hitungTotalHarga(): Int {
        return film.hargaTiket * jumlahTiket
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val film1 = Film("Avengers: Endgame", 50000)
    val film2 = Film("Joker", 45000)
    val film3 = Film("Frozen II", 40000)

    println("==========Selamat datang di Bioskop XXI==========")
    println("===================Daftar Film===================")
    println("|                                                |")
    println("|                                                |")
    println("1. ${film1.judul} - Harga: Rp${film1.hargaTiket}")
    println("2. ${film2.judul} - Harga: Rp${film2.hargaTiket}")
    println("3. ${film3.judul} - Harga: Rp${film3.hargaTiket}")
    println("|                                                |")
    println("|                                                |")
    println("================================================")

    println("Masukkan nomor film yang ingin ditonton:")
    val nomorFilm = scanner.nextInt()

    val filmTerpilih = when (nomorFilm) {
        1 -> film1
        2 -> film2
        3 -> film3
        else -> {
            println("Nomor film tidak valid, memilih film pertama secara default.")
            film1
        }
    }

    println("Masukkan jumlah tiket yang ingin dibeli:")
    var jumlahTiket = scanner.nextInt()

    // Validasi jumlah tiket tidak boleh negatif
    while (jumlahTiket <= 0) {
        println("Jumlah tiket tidak valid. Masukkan kembali jumlah tiket yang ingin dibeli:")
        jumlahTiket = scanner.nextInt()
    }

    val transaksi = Transaksi(filmTerpilih, jumlahTiket)

    val totalHarga = transaksi.hitungTotalHarga()
    println("Total harga yang harus dibayar: Rp$totalHarga")

    println("Masukkan jumlah uang pembayaran:")
    var jumlahBayar = scanner.nextInt()

    // Validasi jumlah uang pembayaran harus mencukupi
    while (jumlahBayar < totalHarga) {
        println("Jumlah uang pembayaran tidak mencukupi. Masukkan kembali jumlah uang pembayaran:")
        jumlahBayar = scanner.nextInt()
    }

    val kembalian = jumlahBayar - totalHarga
    println("Terima kasih telah melakukan pembayaran.")
    println("Kembalian Anda: Rp$kembalian")
}
w