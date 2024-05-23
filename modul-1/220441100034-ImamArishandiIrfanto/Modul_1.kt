fun main() {
    clearConsole()
    println("================================================================")
    println("         Selamat datang di Toko Penyewaan Alat Mendaki!         ")
    println("================================================================")

    val databaseAlat = listOf(
        AlatMendaki("Tenda", 50000),
        AlatMendaki("Sleeping bag", 30000),
        AlatMendaki("Ransel", 20000),
        AlatMendaki("Sepatu gunung", 40000),
        AlatMendaki("Jaket gunung", 35000),
        AlatMendaki("Senter", 15000),
        AlatMendaki("Kompas", 10000),
        AlatMendaki("Peralatan masak", 25000),
        AlatMendaki("Kotak P3K", 10000)
    )

    val pilihanAlat: MutableList<Int> = mutableListOf()
    val jumlahHari: MutableList<Int> = mutableListOf()

    var isLanjutSewa = true

    while (isLanjutSewa) {
        // Menampilkan daftar alat mendaki
        tampilkanDaftarAlat(databaseAlat)

        // Memilih alat mendaki
        pilihAlat(pilihanAlat)

        // Meminta jumlah hari sewa
        print("Masukkan jumlah hari sewa untuk semua alat yang dipilih: ")
        val jumlahHariSewa = readLine()?.toIntOrNull() ?: 0
        jumlahHari.addAll(List(pilihanAlat.size) { jumlahHariSewa })
        println("================================================================")

        // Menghitung total harga sewa
        val totalHarga = hitungTotalHargaSewa(databaseAlat, pilihanAlat, jumlahHari)

        // Menampilkan daftar alat yang disewa
        println("Daftar alat yang disewa:")
        for (i in pilihanAlat.indices) {
            if (i < jumlahHari.size && pilihanAlat[i] - 1 < databaseAlat.size) {
                println("${databaseAlat[pilihanAlat[i] - 1].nama} - ${jumlahHari[i]} hari")
            }
        }
        println("================================================================")

        // Menampilkan total harga sewa
        println("Total harga sewa: Rp$totalHarga")
        println("================================================================")

        // Meminta pembayaran dari pengguna
        var bayar = true
        
        while (bayar) {
            print("Masukkan jumlah pembayaran: Rp")
            val pembayaran = readLine()?.toIntOrNull() ?: 0

            // Menghitung kembalian
            val kembalian = pembayaran - totalHarga
            if (kembalian >= 0) {
                println("Kembalian: Rp$kembalian")
                bayar = false
            } else {
                println("Maaf, uang pembayaran tidak mencukupi.")
                bayar = true
            }
        }
        resetRiwayatSewa(pilihanAlat, jumlahHari)

        // Menanyakan apakah ingin lanjut sewa
        print("Apakah ingin lanjut sewa? (y/n): ")
        val lanjutSewa = readLine()
        isLanjutSewa = lanjutSewa.equals("y", ignoreCase = true)
        println("================================================================")
    }

    println("                 Terima kasih telah berkunjung!                 ")
    println("================================================================")

}

fun tampilkanDaftarAlat(databaseAlat: List<AlatMendaki>) {
    for (i in databaseAlat.indices) {
        println("${i + 1}. ${databaseAlat[i].nama} - Rp${databaseAlat[i].hargaSewa}/hari")
    }
}

fun pilihAlat(pilihanAlat: MutableList<Int>) {
    println("================================================================")
    print("Pilih alat yang ingin disewa (pisahkan dengan koma): ")
    val inputPilihanAlat = readLine()
    val alatTerpilih = inputPilihanAlat?.split(",")?.mapNotNull { it.trim().toIntOrNull() }
    alatTerpilih?.let { pilihanAlat.addAll(it) }
    println("================================================================")
}

fun hitungTotalHargaSewa(databaseAlat: List<AlatMendaki>, pilihanAlat: List<Int>, jumlahHari: List<Int>): Int {
    var totalHarga = 0
    for (i in pilihanAlat.indices) {
        if (i < jumlahHari.size) {
            totalHarga += databaseAlat[pilihanAlat[i] - 1].hargaSewa * jumlahHari[i]
        }
    }
    return totalHarga
}

data class AlatMendaki(val nama: String, val hargaSewa: Int)

fun resetRiwayatSewa(pilihanAlat: MutableList<Int>, jumlahHari: MutableList<Int>) {
    pilihanAlat.clear()
    jumlahHari.clear()
}

fun clearConsole() {
    print("\u001b[H\u001b[2J")
    System.out.flush()
}