class SuperIndo(
    public val menu: List<String>,
    public val harga: List<Int>,
    public val uangDefault: Int = 50000
) {
    fun tampilkanMenu() {
        println("Menu Sayuran:")
        menu.forEachIndexed { index, item -> println("${index + 1}. $item - Rp ${harga[index]}") }
    }

    fun pesanMenu(pesanan: List<Int>): Pair<Int, Int> {
        var totalHarga = 0
        for (index in pesanan) {
            if (index in 0 until menu.size) {
                totalHarga += harga[index]
            } else {
                println("Sayuran dengan nomor ${index + 1} tidak tersedia.")
                continue
            }
        }
        val kembalian = uangDefault - totalHarga
        val kembalianNonNegative = if (kembalian < 0) 0 else kembalian
        return Pair(totalHarga, kembalianNonNegative)
    }
}

fun main() {
    val superIndo = SuperIndo(
        listOf(
            "Bayam", "Kangkung", "Tomat", "Wortel", "Brokoli",
            "Terong", "Bawang Merah", "Bawang Putih", "Kacang Panjang", "Labu"
        ),
        listOf(5000, 4000, 6000, 7000, 8000, 6000, 10000, 12000, 5500, 5000)
    )
    println("== Program Pemesanan Sayuran di Super Indo ==")
    superIndo.tampilkanMenu()
    
    val pesananYangDipilih = listOf(0, 2, 4) // Pesanan ditentukan secara statis
    val (totalHarga, kembalian) = superIndo.pesanMenu(pesananYangDipilih)
    if (totalHarga > 0) {
        println("Pesanan Anda:")
        pesananYangDipilih.forEach { index ->
            println("${superIndo.menu.getOrNull(index) ?: ""} - Rp ${superIndo.harga.getOrNull(index) ?: 0}")
        }
        println("Total Harga: Rp$totalHarga")
        println("Uang yang Anda bayarkan: Rp${superIndo.uangDefault}")
        println("Kembalian: Rp$kembalian")
    }
    
    println("Terima kasih telah menggunakan layanan kami di Super Indo!")
}