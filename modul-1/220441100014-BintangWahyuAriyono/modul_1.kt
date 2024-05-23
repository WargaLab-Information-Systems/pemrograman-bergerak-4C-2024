class Item(val name: String, val price: Int)

class Shop {
    val items = mutableListOf<Item>()

    fun addItem(item: Item) {
        items.add(item)
    }

    fun displayItems() {
        if (items.isEmpty()) {
            println("Toko ini belum memiliki barang.")
        } else {
            println("Barang yang tersedia:")
            for (item in items) {
                println("${item.name}: Rp ${item.price}")
            }
        }
    }
}   

fun main() {
    val shop = Shop()

    shop.addItem(Item("Kue Brownies", 10000)) 
    shop.addItem(Item("Kue Donat", 8000))
    shop.addItem(Item("Bolen", 500))
    shop.addItem(Item("Pastel", 10000)) 
    shop.addItem(Item("Pizza", 8000))
    shop.addItem(Item("Kue balok", 500))

    while (true) {
        println("\nSelamat datang di Toko Jajan & Kue Star!")
        println("1. Tampilkan Barang")
        println("2. Keluar")

        val choice = readLine()

        when (choice) {
            "1" -> shop.displayItems()
            "2" -> {
                println("Terima kasih telah berkunjung!")
                break
            }
            else -> println("Pilihan tidak valid. Silakan pilih lagi.")
        }
    }
}
