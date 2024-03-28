//Nama  : Nisaur Rohmah
//NIM   : 220441100141
//Kelas : Pemrograman Bergerak C

class CalonPenerima(
    val nama: String,
    val nim: String,
    val semester: Int,
    val ipk: Double,
    val pengalamanOrganisasi: Boolean,
    val prestasiAkademik: Boolean
) {

    fun kelayakan(): Boolean {
        val semesterMin = 3
        val semesterMax = 7
        return semester >= semesterMin && semester <= semesterMax && ipk >= 3.0 && (pengalamanOrganisasi || prestasiAkademik)
    }
}

fun main() {
    
    val daftarCalon = arrayOf(
        CalonPenerima("Budi Kurniawan", "12345", 5, 3.8, true, true),
        CalonPenerima("Nisaur Rohmah", "22141", 4, 3.9, true, true),
        CalonPenerima("Eka Ayu Safitri", "22102", 5, 3.7, true, true),
        CalonPenerima("Anindita Galuh", "23456", 3, 3.2, false, true),
        CalonPenerima("Cici Arlinta", "34567", 8, 3.0, true, false),
        CalonPenerima("Deni Andhira", "45678", 7, 2.8, false, false),
        CalonPenerima("Andre Darmawan", "21123", 6, 3.9, false, true),
        CalonPenerima("Trya Irawati", "22456", 3, 3.0, false, false),
        CalonPenerima("Chika Dwi Nanta", "20987", 7, 4.0, true, false),
        CalonPenerima("Cyntya Mariska", "19876", 6, 2.9, true, true),
        CalonPenerima("Alif Fauzia", "7890", 2, 3.8, true, true),
    )
    
    println("==============================================================")
    println("-----------------Hasil Pengumuman Beasiswa--------------------")
    println("==============================================================")

    for (calon in daftarCalon) {
        if (calon.kelayakan()) {
            println("${calon.nama} (${calon.nim}) layak mendapatkan beasiswa")
        } else {
            println("${calon.nama} (${calon.nim}) tidak layak mendapatkan beasiswa")
        }
    }
}