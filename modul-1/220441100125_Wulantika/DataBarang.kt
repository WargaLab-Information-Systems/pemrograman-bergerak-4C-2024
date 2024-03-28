class Barang(val barang: String, val kode: String, val harga: Int, val stok: Int)

fun tampilkanTabel() {
    println("╔═══════════════════════════════════════════╗")
    println("║            Daftar Barang                  ║")
    println("╚═══════════════════════════════════════════╝")
    println("+------+------------+----------+------+------")
    println("| Barang     |    Kode    |  Harga   | Stok |")
    println("+------+------------+----------+------+------")
}

fun tampilkanBarang(barang: Barang) {
    println(
        "| ${barang.barang.padEnd(10)} | ${barang.kode.padEnd(10)} | " +
                "${barang.harga.toString().padEnd(8)} | ${barang.stok.toString().padEnd(4)} |"
    )
}

fun main() {
    val daftarBarang = listOf(
        Barang("Buku", "001", 5000, 20),
        Barang("Pensil", "002", 3000, 20),
        Barang("Penghapus", "003", 2000, 15),
        Barang("Penggaris","004",2000,10),
        Barang("Bolpoin","005",4000,10),
        
    )
  
    tampilkanTabel()
    
    for (barang in daftarBarang) {
        tampilkanBarang(barang)
    }
  
    println("+------+------------+----------+------+")
  
    val jumlahBarang = daftarBarang.size
    if (jumlahBarang > 0) {
        println("Total barang dalam daftar barang: $jumlahBarang")
    } else {
        println("Daftar barang kosong.")
    }
}


