class Gamis(val nama: String, val harga: Int, val ukuran: String) {
    fun tampilkanInfo() {
        println("Nama: $nama")
        println("Harga: Rp $harga")
        println("Ukuran: $ukuran")
    }
}


fun tampilkanMenu() {
    println("=== Toko Gamis ===") 
    println("1. Tampilkan Daftar Gamis")
    println("2. Cari Gamis")
    println("3. Keluar")
}


fun main() {
    val daftarGamis = listOf(
        Gamis("Gamis kecil", 150000, "S"),
        Gamis("Gamis standart", 200000, "M"),
        Gamis("Gamis jumbo", 250000, "L")
    )

    var pilihan: Int

    do {
        tampilkanMenu()
        print("Masukkan pilihan Anda: ")
        pilihan = readLine()?.toIntOrNull() ?: 0

        when (pilihan) {
            1 -> {
                println("=== Daftar Gamis ===")
                for ((index, gamis) in daftarGamis.withIndex()) {
                    println("Gamis ke-${index + 1}:")
                    gamis.tampilkanInfo()
                }
            }
            2 -> {
                print("Masukkan nama gamis yang dicari: ")
                val namaGamisDicari = readLine() ?: ""
                val gamisDitemukan = daftarGamis.find { it.nama.equals(namaGamisDicari, ignoreCase = true) }
                if (gamisDitemukan != null) {
                    println("Gamis ditemukan:")
                    gamisDitemukan.tampilkanInfo()
                } else {
                    println("Gamis tidak ditemukan.")
                }
            }
            3 -> println("Terima kasih telah mengunjungi toko kami.")
            else -> println("Pilihan tidak valid. Silakan pilih menu yang tersedia.")
        }
    } while (pilihan != 3)
}
