var userInput: String = "0" // Ubah dengan input huruf yang diinginkan

fun checkEvenOdd(number: Int): String {
    return if (number % 2 == 0) {
        "genap"
    } else {
        "ganjil"
    }
}

class Student(var name: String, var age: Int) {
    fun printDetails() {
        println("Nama: $name, Usia: $age tahun")
    }
}

fun main() {

    userInput = "muhamad ikhsan ardiyanto"

    val numberOfLetters = userInput.length

    println("Jumlah huruf dalam userInput: $numberOfLetters")

    val numberInput: Int? = numberOfLetters

    if (numberInput != null) {
        if (numberInput > 0) {
            println("Angka yang dimasukkan adalah bilangan positif")
        } else if (numberInput < 0) {
            println("Angka yang dimasukkan adalah bilangan negatif")
        } else {
            println("Angka yang dimasukkan adalah nol")
        }

        println("Mencetak angka dari 1 hingga $numberInput:")
        for (i in 1..numberInput) {
            print("$i ")
        }
        println()

        println("Angka yang dimasukkan adalah ${checkEvenOdd(numberInput)}")
    } else {
        println("Input tidak valid.")
    }

    val student1 = Student("Mikhsana", 20)
    student1.printDetails()
}
