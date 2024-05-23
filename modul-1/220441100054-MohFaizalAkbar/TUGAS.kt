class Mobil(val merk: String, val tahun: Int, val harga: Double) {
    fun tampilkanInfo() {
        println("| Merk  : $merk")
        println("| Tahun : $tahun")
        println("| Harga : $harga")
    }
}

fun cekHargaMobil(mobil: Mobil): String {
    return if (mobil.harga > 100000000) {
        "Mahal"
    } else {
        "Murah"
    }
}

fun main() {
    println("====================================")
    println("|          Informasi Mobil          |")
    println("====================================")

    val mobil1 = Mobil("Toyota", 2020, 150000000.0)
    val mobil2 = Mobil("Honda", 2018, 90000000.0)
    val mobil3 = Mobil("Suzuki", 2015, 80000000.0)

    val daftarMobil = listOf(mobil1, mobil2, mobil3)

    for (mobil in daftarMobil) {
        println("+----------------------------------+")
        mobil.tampilkanInfo()
        val kategoriHarga = cekHargaMobil(mobil)
        println("| Kategori Harga: $kategoriHarga")
    }
    println("====================================")
}