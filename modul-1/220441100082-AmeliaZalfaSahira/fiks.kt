class WiFi(val nama: String, val harga: Int)

class TokoWiFi {
    val wifiTersedia = listOf(
        WiFi("Paket 35 Mbps", 50000),
        WiFi("Paket 20 Mbps", 30000),
        WiFi("Paket 15 Mbps", 20000)
    )

    fun paketWiFi() {
        println("Selamat datang di toko WiFi kami!")
        println("Berikut adalah pilihan WiFi yang tersedia:")
        wifiTersedia.forEachIndexed { index, wifi ->
            println("${index + 1}. ${wifi.nama} - Rp ${wifi.harga}")
        }
    }

    fun beliWiFi(pilihan: Int, saldo: Int): Pair<Int, WiFi?> {
        if (pilihan !in 1..wifiTersedia.size) {
            println("Pilihan tidak valid. Silakan coba lagi.")
            return Pair(saldo, null)
        }
        val wifiTerpilih = wifiTersedia[pilihan - 1]
        if (saldo >= wifiTerpilih.harga) {
            val saldoBaru = saldo - wifiTerpilih.harga
            println("Anda telah membeli ${wifiTerpilih.nama} seharga Rp ${wifiTerpilih.harga}. Selamat menikmati WiFi!")
            println("Saldo Anda sekarang: Rp $saldoBaru")
            return Pair(saldoBaru, wifiTerpilih)
        } else {
            println("Saldo tidak cukup untuk membeli ${wifiTerpilih.nama}.")
            return Pair(saldo, null)
        }
    }
}

fun main() {
    val toko = TokoWiFi()
    var saldo = 5000000
    var lup = true

while (lup){
    println("Selamat datang di toko WiFi kami!")
    toko.paketWiFi()

    print("Masukkan pilihan Anda (1-${toko.wifiTersedia.size}): ")
    val pilihan = readLine()?.toIntOrNull() ?: 0

    val (saldoBaru, wifiTerpilih) = toko.beliWiFi(pilihan, saldo)
    saldo = saldoBaru
    if (wifiTerpilih != null) {
        println("Anda telah membeli ${wifiTerpilih.nama} seharga Rp ${wifiTerpilih.harga}. Selamat menikmati WiFi!")
    }
    print("Apakah Anda ingin melanjutkan belanja? (y/n): ")
        val lanjutkan = readLine()?.toLowerCase()
        lup = lanjutkan != "n"
}
println("Terima kasih telah berbelanja di toko kami. Selamat menikmati WiFi!")
}

