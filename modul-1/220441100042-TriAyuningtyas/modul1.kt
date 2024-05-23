class Baju(val namaPakaian: String, val hargaSewa: Double)

class Penyewaan(val pakaian: Baju, val durasi: Int) {
    fun totalHarga(): Double {
        return pakaian.hargaSewa * durasi
    }
}

fun main() {
    val pilihanBaju = listOf(
        Baju("Kebaya", 100000.0),
        Baju("Jas Pria", 150000.0),
        Baju("Batik Dress", 100000.0),
        Baju("Gaun pengantin", 350000.0)
    )

    println("=================================================")
    println("==============Jasa Penyewaan Baju Nia============")
    println("\nDaftar Pakaian yang tersedia untuk disewakan : ")
    for ((index, item) in pilihanBaju.withIndex()) {
        println("${index + 1}. ${item.namaPakaian} - Rp ${item.hargaSewa} per hari")
    }

    val pesanan = listOf(
        Penyewaan(pilihanBaju[1], 1),
        Penyewaan(pilihanBaju[0], 2)
    )

    if (pesanan.isNotEmpty()) {
        println("\nDetail Pesanan:")
        var totalBelanja = 0.0
        for ((index, pesanan) in pesanan.withIndex()) {
            val totalHarga = pesanan.totalHarga()
            totalBelanja += totalHarga
            println("${index + 1}. ${pesanan.pakaian.namaPakaian} (Durasi: ${pesanan.durasi} hari) - Rp $totalHarga")
        }
        println("Total Tagihan : Rp $totalBelanja")
    } else {
        println("Anda tidak memesan pakaian apa pun")
    }
}