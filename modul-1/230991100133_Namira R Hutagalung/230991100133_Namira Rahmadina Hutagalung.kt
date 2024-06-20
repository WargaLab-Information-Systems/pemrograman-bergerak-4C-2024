class Barang(val namaBarang: String, val masaBerlaku: Int) {

    fun tampilkanBarang() {

        val keterangan = if (masaBerlaku > 2024) "Belum Expired" else "Sudah Expired"

        println("| ${namaBarang.padEnd(40)} | ${masaBerlaku.toString().padEnd(30)} | ${keterangan.padEnd(32)} |")

    }

}

fun main() {

    val daftarBarang = mutableListOf<Barang>()

    daftarBarang.add(Barang("Richeese Nabati", 2025))
    daftarBarang.add(Barang("Roti Hatari", 2019))
    daftarBarang.add(Barang("Taro Rumput Laut", 2020))
    daftarBarang.add(Barang("Ultramilk Cokelat", 2030))
    daftarBarang.add(Barang("Biskuit Saltcheese", 2025))
    daftarBarang.add(Barang("Roti Marine", 2026))
  
    println("----------------------------------------------------------------------------------------------------------------")
    println("|                                       TOKO GROSIR SURYA                                                      |")    
    println("----------------------------------------------------------------------------------------------------------------")
    println("+------------------------------------------+--------------------------------+----------------------------------+")

    println("|             Nama Barang                  |        Masa Berlaku            |           Keterangan             |")

    println("+------------------------------------------+--------------------------------+----------------------------------+")

    for (barang in daftarBarang) {
        barang.tampilkanBarang()
    }

    println("+------------------------------------------+--------------------------------+----------------------------------+")
}