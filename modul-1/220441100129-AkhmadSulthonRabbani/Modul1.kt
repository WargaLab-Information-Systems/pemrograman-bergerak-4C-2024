fun main() {
    // Membuat objek warung minuman
    val warung = WarungMinuman("Warung Minuman Segar")

    // Menambahkan minuman ke menu
    warung.addDrink(Drink("Chizu Matcha", 19000))
    warung.addDrink(Drink("Cokolateh", 17000))
    warung.addDrink(Drink("Esteh Mango Greentea", 17000))
    warung.addDrink(Drink("Esteh Lemonade Berry", 16000))
    warung.addDrink(Drink("Chizu Red Velvet", 19000))
    warung.addDrink(Drink("Chizu Avocado", 19000))
    warung.addDrink(Drink("Chizu Taro", 19000))
    warung.addDrink(Drink("Esteh Susu Nusaberry", 16000))

    // Menampilkan menu
    warung.showMenu()

    // Mengambil input dari pengguna
    println("\nMasukkan nomor minuman yang ingin dipesan (pisahkan dengan koma jika memesan lebih dari satu):")
    val drinkIndexes = readLine()?.split(",")?.map { it.trim().toIntOrNull() ?: 0 } ?: listOf()

    // Memilih jumlah minuman yang akan dibeli
    println("Masukkan jumlah masing-masing minuman yang ingin dibeli (pisahkan dengan koma jika memesan lebih dari satu):")
    val quantities = readLine()?.split(",")?.map { it.trim().toIntOrNull() ?: 0 } ?: listOf()

    // Memeriksa apakah minuman tersedia dan memproses pesanan
    var total = 0
    for ((index, drinkIndex) in drinkIndexes.withIndex()) {
        val quantity = if (index < quantities.size) quantities[index] else 1
        val drink = warung.orderDrink(drinkIndex, quantity)
        if (drink != null) {
            total += drink.price * quantity
        }
    }

    if (total > 0) {
        println("\nTotal Harga: Rp$total")

        // Memasukkan uang tunai
        println("Masukkan uang tunai:")
        val cash = readLine()?.toIntOrNull() ?: 0

        // Menghitung kembalian
        val change = cash - total
        if (change >= 0) {
            println("Kembalian Anda: Rp$change")
        } else {
            println("Maaf, uang tunai tidak mencukupi.")
        }
    } else {
        println("Maaf, minuman tidak tersedia atau jumlah minuman tidak valid.")
    }
}

// Class untuk merepresentasikan minuman
class Drink(val name: String, val price: Int)

// Class untuk merepresentasikan warung minuman
class WarungMinuman(val name: String) {
    private val menu = mutableListOf<Drink>()

    // Function untuk menambahkan minuman ke menu
    fun addDrink(drink: Drink) {
        menu.add(drink)
    }

    // Function untuk menampilkan menu
    fun showMenu() {
        println("Menu di $name:")
        menu.forEachIndexed { index, drink -> println("${index + 1}. ${drink.name} - Rp${drink.price}") }
    }

    // Function untuk memesan minuman
    fun orderDrink(index: Int, quantity: Int): Drink? {
        return menu.getOrNull(index - 1)?.let { drink ->
            if (quantity > 0) drink else null
        }
    }
}