fun main() {
    val sisiPersegi: Int = 10

    class Persegi(private val sisi: Int) {

        fun hitungLuas(): Int {
            return sisi * sisi
        }

        fun hitungKeliling(): Int {
            return 4 * sisi
        }
    }
    
    if (sisiPersegi > 0) {
        val persegi = Persegi(sisiPersegi)
        println("Luas persegi: ${persegi.hitungLuas()}")
        println("Keliling persegi: ${persegi.hitungKeliling()}")
    } else {
        println("Nilai sisinya harus lebih dari 0")
    }

    println("\nTabel Luas dan Keliling Persegi")
    println("Sisi | Luas | Keliling")
    println("-----|------|--------|")
    for (i in 1..10) {
		val persegi = Persegi(i)
        println("< $i  | ${persegi.hitungLuas()} | ${persegi.hitungKeliling()} >")
    }
}