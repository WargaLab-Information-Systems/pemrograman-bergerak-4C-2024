class PetShop(
    var barang: String,
    var harga: Double,
    var jumlah: Int
) {

    fun hitungTotal(): Int {
        val hargaTotal = 
        if (barang == "lifecat") 11000
        else 12000
        return hargaTotal*jumlah
    }

    fun struk() {
        println("\n\n------- STRUK PEMBELIAN -------")
        println("Barang: $barang")
        println("Harga: Rp $harga")
        println("Jumlah: $jumlah")
        println("Total: Rp ${hitungTotal()}")
    }
}

fun main() {
    var isRunning = true

    while (isRunning) {
        println("----- SELAMAT DATANG -----")
        println("SILAHKAN PILIH MENU DI BAWAH INI\n")
        println("1. Melakukan pemesanan")
        println("2. Batal")

        val menu = 1
        println("\nanda memilih menu: $menu\n")

        when (menu) {
            1 -> {
                val memesan = "ya"
                if (memesan == "ya") {
                    val barang = "lifecat"
                    println("Masukkan merk barang: $barang")

                    val harga = 11000.0 // Harga diubah menjadi tipe data Double
                    println("Harga perpcs: $harga")

                    val jumlah = 3 // Jumlah diubah menjadi tipe data Int
                    println("Jumlah: $jumlah")

                    val beli = PetShop(barang, harga, jumlah)
                    beli.struk() // Mengubah pemanggilan Struk() menjadi struk()
                    println("\nBerhasil membeli barang")
                }
                isRunning = false
            }
            2 -> {
                println("Anda telah keluar dari menu")
                isRunning = false
            }
            else -> {
                println("Silahkan memilih ulang menu dengan benar!")
            }
        }
    }
}
