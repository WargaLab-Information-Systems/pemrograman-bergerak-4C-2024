fun main() {
// Definisi class
open class Ps(val name: String, val species: String) {
    open fun bermain(): String {
        return ""
    }
}

// Subclass
class Ps1(name: String) : Ps(name, "Jeremy") {
    override fun bermain(): String {
        return "main efootball"
    }
}

class Ps2(name: String) : Ps(name, "Kaito") {
    override fun bermain(): String {
        return "main naruto"
    }
}

// Function
fun printPsbermain(ps: Ps) {
    println("${ps.name} sedang ${ps.bermain()}")
}

// Variabel
val pss = listOf(
    Ps1("Jeremy"),
    Ps2("Kaito")
)

// Looping
println("Daftar siapa dan game yang dimainkan:")
for (ps in pss) {
    printPsbermain(ps)
}

// If else
for (ps in pss) {
    val psType = if (ps is Ps1) {
        "Jeremy"
    } else if (ps is Ps2) {
        "Kaito"
    } else {
        "Auauau"
    }
    println("${ps.name} adalah $psType")
}

}