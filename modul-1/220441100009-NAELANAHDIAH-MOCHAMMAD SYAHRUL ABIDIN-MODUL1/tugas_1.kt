import kotlin.system.exitProcess

// Class untuk merepresentasikan makanan
class Food(val name: String, val price: Int)

// Class untuk merepresentasikan warung makan
class WarungMakan(val name: String) {
    private val menu = mutableListOf<Food>()

    // Function untuk menambahkan makanan ke menu
    fun addFood(food: Food) {
        menu.add(food)
    }

    // Function untuk menampilkan menu
    fun showMenu() {
        println("Menu di $name:")
        menu.forEachIndexed { index, food -> println("${index + 1}. ${food.name} - Rp${food.price}") }
    }

    // Function untuk memesan makanan
    fun orderFood(index: Int, quantity: Int): Food? {
        return menu.getOrNull(index - 1)?.let { food ->
            if (quantity > 0) food else null
        }
    }
}

fun main() {
    // Membuat objek warung makan
    val warung = WarungMakan("Warung Makan")

    // Menambahkan makanan ke menu
    warung.addFood(Food("Rawon", 13000))
    warung.addFood(Food("Gule", 17500))
    warung.addFood(Food("Bakso", 10000))
    warung.addFood(Food("Mie Ayam", 15500))
    warung.addFood(Food("Sate Ayam", 16000))
    warung.addFood(Food("Es Teh", 3000))
    warung.addFood(Food("Es Jeruk", 4000))

    // List untuk menyimpan pesanan
    val pesanan = mutableListOf<Pair<Food, Int>>()

    // Looping utama
    while (true) {
        // Menampilkan menu
        warung.showMenu()

        // Mengambil input dari pengguna
        println("\nMasukkan nomor makanan yang ingin dipesan atau 0 untuk keluar:")
        val foodIndex = readLine()?.toIntOrNull() ?: 0

        // Keluar dari loop jika pengguna memilih 0
        if (foodIndex == 0) {
            break
        }

        // Memilih jumlah makanan yang akan dibeli
        println("Masukkan jumlah yang ingin dibeli:")
        val quantity = readLine()?.toIntOrNull() ?: 0

        // Memeriksa apakah makanan tersedia
        val food = warung.orderFood(foodIndex, quantity)
        if (food != null) {
            pesanan.add(food to quantity)

            // Memeriksa apakah pengguna ingin menambahkan menu lagi
            println("\nApakah Anda ingin menambahkan menu lagi? (Ya/Tidak)")
            val tambahMenu = readLine()?.equals("Ya", ignoreCase = true) ?: false
            if (!tambahMenu) {
                break
            }
        } else {
            println("Maaf, tidak tersedia.")
        }
    }

    // Menampilkan pesanan
    println("\nPesanan Anda:")
    var totalHarga = 0
    pesanan.forEachIndexed { index, pair ->
        val (food, quantity) = pair
        val subtotal = food.price * quantity
        println("${index + 1}. ${food.name} - ${quantity}x Rp${food.price} = Rp$subtotal")
        totalHarga += subtotal
    }

    // Menampilkan total harga dan meminta pembayaran
    println("\nTotal Harga: Rp$totalHarga")
    println("Masukkan uang tunai:")
    val cash = readLine()?.toIntOrNull() ?: 0

    // Menghitung kembalian
    val change = cash - totalHarga
    if (change >= 0) {
        println("Kembalian Anda: Rp$change")
    } else {
        println("Maaf, uang tunai tidak mencukupi.")
    }
}