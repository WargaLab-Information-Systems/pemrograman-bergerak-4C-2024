// function
fun daftar() {
    val gamelist = arrayOf(
        "MOBILE LEGENDS" to 50000,
        "FREE FIRE" to 40000,
        "PUBG" to 60000,
        "GENSHIN IMPACT" to 80000,
        "EFOOTBALL" to 30000
    )

    println("DAFTAR TOP UP GAME:")
    println("")
    for ((game, harga) in gamelist) {
        println("$game = RP.$harga")
    }
}
fun garis (){
    var garis1 = 1
    while (garis1 < 2){
        garis1++
        println("==================================")
    }
}

fun beli (){
    var jumlah = 2
    if (jumlah == 2){
        println("Kamu memilih game : ")
        println("1. MOBILE LEGENDS")
        println("2. FREE FIRE")
    } else if (jumlah > 2){
        println("Masukkan pilihan anda dengan benar!!!")
    }
 
}
fun pembeli(){
    val orang = Orang()
    println(orang.identitas())
}
 class Orang() {      
    fun identitas() = "Buyer = Al"
}
 fun main() {
    println("||==================================||")
    println("||--------------WELCOME-------------||")
    println("||==================================||")
    println("")
    daftar()
    println("")
    garis()
    beli()
    garis()
    println("")
    pembeli()
    println("")
    println("-----------THANK YOU-----------")
}