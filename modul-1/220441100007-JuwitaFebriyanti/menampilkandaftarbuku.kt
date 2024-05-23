class Buku(val judul: String, val pengarang: String, val tahunTerbit: Int) {
    fun tampilkanBuku() {
        val keterangan = if (tahunTerbit > 2000) "Buku modern" else "Buku klasik"
        println("| ${judul.padEnd(40)} | ${pengarang.padEnd(30)} | ${tahunTerbit.toString().padEnd(13)} | ${keterangan.padEnd(18)} |")
    }
}

fun main() {
    val daftarBuku = mutableListOf<Buku>()

    daftarBuku.add(Buku("5 CM", "Donny Dhirgantoro", 2005))
    daftarBuku.add(Buku("Bumi", "Tere Liye", 2014))
    daftarBuku.add(Buku("Bumi Manusia", "Pramoedya Ananta Toer", 1980))
    daftarBuku.add(Buku("Dilan: Dia adalah Dilanku tahun 1990", "Pidi Baiq", 2014))
    daftarBuku.add(Buku("Laskar Pelangi", "Andrea Hirata", 2005))
    daftarBuku.add(Buku("Laut Bercerita", "Leila Salikha Chudori", 2017))
    daftarBuku.add(Buku("Lupus", "Hilman Hariwijaya", 1986))
    daftarBuku.add(Buku("Perahu Kertas", "Dewi Lestari", 2003))
    daftarBuku.add(Buku("Tenggelamnya Kapal Van der Wijck", "Abdul Malik Karim Amrullah", 1938))
    daftarBuku.add(Buku("Sang Pemimpi", "Andrea Hirata", 2006))

    println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗")
    println("║             					  Daftar Buku               				         ║")
    println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝")
    println("+------------------------------------------+--------------------------------+---------------+--------------------+")
    println("|                 Judul                    |           Pengarang            | Tahun Terbit  |     Keterangan     |")
    println("+------------------------------------------+--------------------------------+---------------+--------------------+")

    for (buku in daftarBuku) {
        buku.tampilkanBuku()
    }

    println("+------------------------------------------+--------------------------------+---------------+--------------------+")
}