class Obat(val nama: String, var jumlah: Int, var harga: Double) {
    
    fun tambahStok(jumlahTambah: Int) {
        jumlah += jumlahTambah
    }

    fun kurangiStok(jumlahKurang: Int) {
        if (jumlah - jumlahKurang >= 0) {
            jumlah -= jumlahKurang
        } else {
            println("Stok tidak mencukupi!")
        }
    }
    
    fun hitungTotalHarga(jumlahBeli: Int): Double {
        return jumlahBeli * harga
    }
}

fun pembeli() {
    println("||==================================||")
    println("||----------SELAMAT DATANG----------||")
    println("||==================================||")
}

fun daftar() {
    println("Daftar Belanja:")
    val daftarObat = listOf(
        Obat("Paracetamol", 3, 2000.0),
        Obat("CTM", 5, 3000.0),
        Obat("Amoxilin", 7, 5000.0)
    )
    
    for (obat in daftarObat) {
        println("${obat.nama}: Stok ${obat.jumlah}, Harga: Rp ${obat.harga}")
    }
}

fun tanda() {
    println("======================================")
}

fun beli() {
    println("Setelah pembelian:")
    val daftarObat = listOf(
        Obat("Paracetamol", 3, 2000.0),
        Obat("CTM", 5, 3000.0),
        Obat("Amoxilin", 7, 5000.0)
    )
    
    for (obat in daftarObat) {
        obat.kurangiStok(1)
    }
    
    for (obat in daftarObat) {
        println("${obat.nama}: Stok ${obat.jumlah}")
    }
    
    val totalHargaParacetamol = daftarObat[0].hitungTotalHarga(2)
    val totalHargaAmoxilin = daftarObat[2].hitungTotalHarga(2)
    val totalHarga = totalHargaParacetamol + totalHargaAmoxilin
    
    val totalUang = 20000.0
    println("Total harga belanjaan: Rp $totalHarga")
    
    cekUangPembeli(totalUang, totalHarga)
}

fun cekUangPembeli(totalUang: Double, totalHarga: Double) {
    if (totalUang >= totalHarga) {
        val kembalian = totalUang - totalHarga
        println("Kembalian: Rp $kembalian")
    } else {
        println("Maaf, total uang tidak mencukupi.")
    }
}

fun main() {
    println("")
    pembeli()
    println("")
    daftar()
    println("")
    tanda()
    beli()
    tanda()
    println("")
    println("-----------TERIMA KASIH-----------")
}