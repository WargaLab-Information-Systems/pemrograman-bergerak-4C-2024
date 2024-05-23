class Item(
    val name: String,
    val price: Int
)

class Shop {
    val items = mutableListOf<Item>()

    fun addItem(item: Item) {
        items.add(item)
    }

    fun displayItems() {
        if (items.isEmpty()) {
            println("Toko ini belum memiliki barang.")
        } else {
            println("Barang yang tersedia: \n==============================" )
            for (item in items) {
                println("${item.name}: Rp ${item.price}")
            }
        }
    }
}   
fun main() {
    val shop = Shop()

    shop.addItem(Item()) 
    shop.addItem(Item("Adidas Samba     ", 2200000))
    shop.addItem(Item("Adidas Campus    ", 900000))
    shop.addItem(Item("Adidas Bali      ", 2400000)) 
    shop.addItem(Item("Adidas Gazzele   ", 800000))
    shop.addItem(Item("Adidas Stan Smith", 700000))

    while (true) {
        println("\nSelamat Belanja DiToko Kami")
        println("1. Tampilkan Barang")
        println("2. Keluar")

        val choice = readLine()

        when (choice) {
            "1" -> shop.displayItems()
            "2" -> {
                println("Terima kasih telah berkunjung! \n    #In Badkids We Trust#")
            }
            else -> println("Pilihan Salah. Silakan pilih lagi.")
        }
    }
}