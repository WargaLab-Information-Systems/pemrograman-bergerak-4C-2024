//Eka Ayu safitri
//220441100102
//Pemrograman Bergerak-4C
// Program Sistem Pendataan Kependudukan

    // Deklarasi data
    val daftarPenduduk = arrayOf(
        Penduduk("Andini", "Jl. Ahmad Yani 1", 21, "1234567890123456", "A"),
        Penduduk("Badrul", "Jl. Gunungsari 2", 27, "2345678901234567", "B"),
        Penduduk("Caca", "Jl. Joyoboyo 3", 20, "3456789012345678", "AB"),
        Penduduk("Doni", "Jl. Siwalankerto 4", 25, "4567890123456789", "O"),
        Penduduk("Eka", "Jl. Tandes Lor 5", 22, "5678901234567890", "A")
    )

    // Class untuk menampung data penduduk
    class Penduduk(
        var nama: String,
        val alamat: String,
        var usia: Int,
        val nik: String,
    	var golonganDarah: String
    )

    // Function untuk menampilkan daftar penduduk
    fun tampilDaftarPenduduk() {
        
        println("==============================================================")
        println("-----------------Sistem Pendataan Kependudukan----------------")
        println("==============================================================")
        println("                                             Date : 23/04/2024")
        println("==============================================================")
        println("------------------------Daftar Penduduk-----------------------")
        println("==============================================================")
        for (penduduk in daftarPenduduk) {
            println()
            println("Nama          : " + penduduk.nama)
            println("Alamat        : " + penduduk.alamat)
            println("Usia          : " + penduduk.usia)
            println("Nik           : " + penduduk.nik)
            println("Golongan Darah: " + penduduk.golonganDarah)
            println("--------------------------------------------------------------")
            println()
        }
	}

    fun cariPenduduk(nama: String): Penduduk? {
        for (penduduk in daftarPenduduk) {
            if (penduduk.nama.toLowerCase() == nama.toLowerCase()) {
                return penduduk
            }
        }
        return null
    }

    // Function untuk menampilkan informasi penduduk
    fun tampilInformasiPenduduk(penduduk: Penduduk) {
        println("==============================================================")
        println("----------------------Informasi Penduduk----------------------")
        println("==============================================================")
        println("Nama          : " + penduduk.nama)
        println("Alamat        : " + penduduk.alamat)
        println("Usia          : " + penduduk.usia)
        println("Nik           : " + penduduk.nik)
        println("Golongan Darah: " + penduduk.golonganDarah)
        println("--------------------------------------------------------------")
    }

    // Main function
    fun main() {
        
        tampilDaftarPenduduk()
        println("==============================================================")
        println("----------------------------Update----------------------------")
        daftarPenduduk[0].nama = "Aldi"
        daftarPenduduk[1].nama = "Bara"
        daftarPenduduk[2].nama = "Cinta"
        daftarPenduduk[3].nama = "Dara"
        daftarPenduduk[4].nama = "Eko"
        daftarPenduduk[0].usia = 31
        daftarPenduduk[1].usia = 37
        daftarPenduduk[2].usia = 30
        daftarPenduduk[3].usia = 35
        daftarPenduduk[4].usia = 32
        daftarPenduduk[0].golonganDarah = "B"
        daftarPenduduk[1].golonganDarah = "A"
        daftarPenduduk[2].golonganDarah = "-AB"
        daftarPenduduk[3].golonganDarah = "A"
        daftarPenduduk[4].golonganDarah = "O"
        tampilDaftarPenduduk()
        println()

        // Cari penduduk berdasarkan nama
        val namaPenduduk = "cinta"
        val penduduk = cariPenduduk(namaPenduduk)

        if (penduduk != null) {
            tampilInformasiPenduduk(penduduk)
        } else {
            println("==============================================================")
        	println("---------------------Informasi Penduduk-----------------------")
            println("==============================================================")
            println("Nama penduduk tidak ditemukan.")
        }
    }