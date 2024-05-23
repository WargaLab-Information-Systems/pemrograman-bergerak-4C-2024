    Solutions
    Docs
    Community
    Teach
    Play

1.9.23
JVM

// Class untuk merepresentasikan kamar kost

class Kamar(val nomorKamar: Int, val hargaKamar: Int, var tersedia: Boolean = true) {

​

    // Fungsi untuk menampilkan informasi kamar

    fun tampilkanInfo(): String {

        val status = if (tersedia) "Tersedia" else "Tidak Tersedia"

        return "Nomor Kamar: $nomorKamar, Harga: $hargaKamar, Status: $status"

    }

}

​

// Class untuk mengelola daftar kamar kost

class PengelolaKost {

    private val daftarKamar = mutableListOf<Kamar>()

​

    // Fungsi untuk menambahkan kamar ke daftar

    fun tambahkanKamar(kamar: Kamar) {

        daftarKamar.add(kamar)

    }

​

    // Fungsi untuk menampilkan semua kamar yang tersedia

    fun tampilkanKamarTersedia(): List<String> {

        val kamarTersedia = mutableListOf<String>()

        for (kamar in daftarKamar) {

            if (kamar.tersedia) {

                kamarTersedia.add(kamar.tampilkanInfo())

            }

        }

        return kamarTersedia

    }

​

    // Fungsi untuk menyewa kamar

    fun sewaKamar(nomorKamar: Int): String {

        for (kamar in daftarKamar) {

            if (kamar.nomorKamar == nomorKamar && kamar.tersedia) {

                kamar.tersedia = false

                return "Kamu mau kamar nomor $nomorKamar yaa? berhasil disewa kok, terimakasih banyak!"

            }

        }

        return "Kamar yang kamu pilih kamar nomor $nomorKamar yaa? maaf, belum ada nih!!"

    }

}

​

fun main() {

    val pengelola = PengelolaKost()

    // Inisialisasi kamar kost secara statis

    pengelola.tambahkanKamar(Kamar(1, 500000))

    pengelola.tambahkanKamar(Kamar(2, 600000))

    pengelola.tambahkanKamar(Kamar(3, 700000))

    

    println("SELAMAT DATANG DI KOST CAHAYA")

    println("----------------------------------")

    

    // Buat Menampilkan kamar kost yang tersedia

    val kamarTersedia = pengelola.tampilkanKamarTersedia()

    if (kamarTersedia.isNotEmpty()) {

        println("Daftar Kamar Tersedia:")

        kamarTersedia.forEach { println(it) }

    } else {

        println("Maaf, tidak ada kamar yang tersedia saat ini.")

    }

​

    println("----------------------------------")

    

    // buat nyewa kamar

​

    val nomorKamarSewa = 4

​

    println(pengelola.sewaKamar(nomorKamarSewa))

}

