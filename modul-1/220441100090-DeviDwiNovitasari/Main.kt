class CafeDeee {
    var name : String = ""
    var design : String = ""
    var tulisan : String = ""
}
fun main(){
    val Cafe = CafeDeee()
    Cafe.design ="==============================================="
    Cafe.name = "=============WELCOME TO CAFE DEEE==============="
    Cafe.design ="==============================================="
    println(" ${Cafe.design}")
    println(" ${Cafe.name}")
    println(" ${Cafe.design}")
    cafe()
}
    fun cafe() {
        val Cafe = CafeDeee()
        Cafe.tulisan = "Menu yang anda pesan "
        do {
            println("")
            println("Menu Minuman")
            println("1. Es Teh")
            println("2. Es Degan")
            println("3. Es Dawet")
            println("4. Es Joshua")
            println("5. Es Coklat")
            println("")
            println("Pilih Menu:")
            when (readLine()?.toIntOrNull()) {
                1 -> {
                    println("${Cafe.tulisan}Es Teh")
                    println("Harga Rp. 3000")
                    println("Silahkan Lakukan Pembayaran : ")
                    val masukkan_uang = readLine()?.toIntOrNull() ?: 0
                        if (masukkan_uang < 3000) {
                            println("Pembayaran anda kurang")
                        } else if(masukkan_uang > 3000) {
                            println("Pembayaran anda kelebihan")
                        } else {
                            println("Pembayaran anda benar")
                        }
                } 2 -> {
                    println("${Cafe.tulisan}Es Degan")
                    println("Harga Rp. 5000")
                    println("Silahkan Lakukan Pembayaran : ")
                    val masukkan_uang = readLine()?.toIntOrNull() ?: 0
                    if (masukkan_uang < 5000) {
                        println("Pembayaran anda kurang")
                    } else if(masukkan_uang > 5000) {
                        println("Pembayaran anda kelebihan")
                    } else {
                        println("Pembayaran anda benar")
                    }
                } 3 -> {
                    println("${Cafe.tulisan}Es Dawet")
                    println("Harga Rp. 5000")
                    println("Silahkan Lakukan Pembayaran : ")
                    val masukkan_uang = readLine()?.toIntOrNull() ?: 0
                    if (masukkan_uang < 5000) {
                        println("Pembayaran anda kurang")
                    } else if(masukkan_uang > 5000) {
                        println("Pembayaran anda kelebihan")
                    } else {
                        println("Pembayaran anda benar")
                    }
                } 4 -> {
                    println("${Cafe.tulisan}Es Joshua")
                    println("Harga Rp. 7000")
                    println("Silahkan Lakukan Pembayaran : ")
                    val masukkan_uang = readLine()?.toIntOrNull() ?: 0
                    if (masukkan_uang < 7000) {
                        println("Pembayaran anda kurang")
                    } else if(masukkan_uang > 7000) {
                        println("Pembayaran anda kelebihan")
                    } else {
                        println("Pembayaran anda benar")
                    }
                } 5 -> {
                    println("${Cafe.tulisan}Es Coklat")
                    println("Harga Rp. 10000")
                    println("Silahkan Lakukan Pembayaran : ")
                    val masukkan_uang = readLine()?.toIntOrNull() ?: 0
                    if (masukkan_uang < 10000) {
                        println("Pembayaran anda kurang")
                    } else if(masukkan_uang > 10000) {
                        println("Pembayaran anda kelebihan")
                    } else {
                        println("Pembayaran anda benar")
                    }
                }
                else -> println("Menu tdk valid")
            }
            print("Apakah anda lanjut ? (y/n)")
            var lanjut:Boolean = true
            try {
                val input = readLine()!!.toLowerCase()
                when (input) {
                    "y" -> lanjut = true
                    "n" -> lanjut = false
                    else -> throw IllegalArgumentException("input tdk valid")
                }
            }catch (e: IllegalArgumentException) {
                println(e.message)
                lanjut = true
            }
        } while (lanjut)
        println("Tdk valid")
    }
