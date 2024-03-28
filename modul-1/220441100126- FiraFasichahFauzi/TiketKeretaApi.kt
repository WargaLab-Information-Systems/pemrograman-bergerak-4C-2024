class TiketKereta(
    val namaPenumpang: String,
    val tujuan: String,
    val kelas: String,
    val harga: Int
) {
    fun cetakTiket() {
        println("╔═════════════════════════════════════════╗")
        println("║           Detail Tiket Kereta           ║")
        println("╠═════════════════════════════════════════╣")
        println("║ Nama Penumpang : ${namaPenumpang.padEnd(23)}║")
        println("║ Tujuan         : ${tujuan.padEnd(23)}║")
        println("║ Kelas          : ${kelas.padEnd(23)}║")
        println("║ Harga          : Rp.${harga.toString().padEnd(20)}║")
        println("╚═════════════════════════════════════════╝")
    }
}

fun main() {
    val daftarTiket = listOf(
        TiketKereta("Agus", "Surabaya - Malang", "Ekonomi", 45000),
        TiketKereta("Budi", "Surabaya - Bojonegoro", "Ekonomi", 95000),
        TiketKereta("Citra", "Surabaya - Pekalongan", "Bisnis", 225000),
        TiketKereta("Liam", "Surabaya - Semarang", "Eksekutif", 400000),
        TiketKereta("Putri", "Surabaya - Jakarta", "Eksekutif", 515000),
        TiketKereta("Dinda", "Surabaya - Bandung", "Eksekutif", 540000),
        TiketKereta("Satria", "Surabaya - Lamongan", "Ekonomi", 95000),
        TiketKereta("Lilis", "Surabaya - Probolinggo", "Ekonomi", 190000),
        TiketKereta("Wulan", "Surabaya - Pasuruan", "Ekonomi", 190000),
        TiketKereta("Arif", "Surabaya - Cirebon", "Eksekutif", 320000)
    )

    for ((index, tiket) in daftarTiket.withIndex()) {
        println("")
        println("Tiket ke-${index + 1}:")
        tiket.cetakTiket()

        val diskon = if (tiket.harga > 400000) {
            0.1 * tiket.harga // Diskon 10% untuk harga tiket di atas 400.000
        } else {
            0.0 // Tidak ada diskon untuk harga tiket di bawah atau sama dengan 400.000
        }

        val hargaSetelahDiskon = tiket.harga - diskon.toInt()

        println("Harga setelah diskon: Rp.$hargaSetelahDiskon")
        println("-------------------------------------------")
    }
}