open class Menu(var nama: String, var harga: Int) {
    fun deskripsi(): String {
        return "$nama Harga: Rp$harga"
    }
}

class Original : Menu("Bakso Original", 10000) {
    val topping = listOf("Bakso", "Pangsit")
}

class Lava : Menu("Bakso Lava", 15000) {
    val topping = listOf("Bakso", "Pangsit", "Full Cabe")
}

class Jumbo : Menu("Bakso Jumbo", 20000) {
    val topping = listOf("Bakso besar", "Pangsit", "+bakso")
}

class Mie : Menu("Mie Ayam", 10000) {
    val topping = listOf("Mie", "Pangsit", "Ayam Suwir")
}

fun main() {
    val original = Original()
    val lava = Lava()
    val jumbo = Jumbo()
    val mie = Mie()

    println("Menu yang tersedia:")
    println(original.deskripsi())
    println(lava.deskripsi())
    println(jumbo.deskripsi())
    println(mie.deskripsi())

    //Transaksi
    val pilih = mie
    val nominaluang = 20000

    if (nominaluang >= pilih.harga) {
        println("\nAnda membeli ${pilih.nama}.")
        println("Topping:")
        for (toping in pilih.topping) {
            println("- $toping")
        }
        println("Kembalian anda : Rp${nominaluang - pilih.harga}")

    } else {
        println("\nMaaf, Uang anda tidak cukup untuk membeli ${pilih.nama}.")
    }
}