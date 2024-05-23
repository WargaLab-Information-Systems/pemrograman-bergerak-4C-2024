fun main() {
    val pedagang = dagang()
    pedagang.warung()
}

class dagang {
    fun warung(){
        var lanjutkan: String

        do {
            println("Selamat Datang di Kedai Berkah")
            println("==============================")
            println("Daftar menu")
            println("""
                1. Nasi Goreng Jawa = Rp. 15.000
                2. Soto Lamongan = Rp. 10.000
                3. Bebek Bumbu Hitam = Rp. 25.000
                4. Ayam Bumbu Hitam = Rp. 20.000
                5. Ayam Geprek = Rp. 10.000""")


            var pesan = 0
            while (true) {
                print("Mau pesan yang mana (tulis nomornya)? ")
                pesan = readLine()!!.toInt()
                if (pesan in 1..5) {
                    break 
                } else {
                    println("Maaf, nomor pesanan tidak valid. Silakan pilih nomor pesanan yang sesuai.")
                }
            }

            if (pesan == 1) {
                println("Anda memesan Nasi Gorengan dengan harga Rp. 15.000")
                println("Total pembayaran adalah Rp. 15.000")
                
                var bayar = 0
                while (true) {
                    print("Mohon masukkan total bayar: ")
                    bayar = readLine()!!.toInt()
                    if (bayar >= 15000) {
                        break 
                    } else {
                        println("Pembayaran tidak mencukupi. Mohon masukkan jumlah yang sesuai.")
                    }
                } 
                val kembalian = bayar - 15000
                println("Kembalian anda adalah Rp. $kembalian")
            } else if (pesan == 2){
                println("anda memesan Soto Lamongan dengan harga Rp. 10.000")
                println("Total pembayaran adalah Rp. 10.000")
                
                var bayar = 0
                while (true) {
                    print("Mohon masukkan total bayar: ")
                    bayar = readLine()!!.toInt()
                    if (bayar >= 10000) {
                        break 
                    } else {
                        println("Pembayaran tidak mencukupi. Mohon masukkan jumlah yang sesuai.")
                    }
                } 
                val kembalian = bayar - 10000
                println("Kembalian anda adalah Rp. $kembalian")
                println("Terima kasih atas kunjungannya")
            } else if (pesan == 3){
                println("anda memesan Bebek Bumbu Hitam dengan harga Rp. 25.000")
                println("Total pembayaran adalah Rp. 25.000")
                
                var bayar = 0
                while (true) {
                    print("Mohon masukkan total bayar: ")
                    bayar = readLine()!!.toInt()
                    if (bayar >= 25000) {
                        break 
                    } else {
                        println("Pembayaran tidak mencukupi. Mohon masukkan jumlah yang sesuai.")
                    }
                } 
                val kembalian = bayar - 25000
                println("Kembalian anda adalah Rp. $kembalian")
                println("Terima kasih atas kunjungannya")
            } else if (pesan == 4){
                println("anda memesan Ayam Bumbu Hitam dengan harga Rp. 20.000")
                println("Total pembayaran adalah Rp. 20.000")
                
                var bayar = 0
                while (true) {
                    print("Mohon masukkan total bayar: ")
                    bayar = readLine()!!.toInt()
                    if (bayar >= 20000) {
                        break 
                    } else {
                        println("Pembayaran tidak mencukupi. Mohon masukkan jumlah yang sesuai.")
                    }
                } 
                val kembalian = bayar - 20000
                println("Kembalian anda adalah Rp. $kembalian")
                println("Terima kasih atas kunjungannya")
            } else if (pesan == 5){
                println("anda memesan Ayam Geprek dengan harga Rp. 10.000")
                println("Total pembayaran adalah Rp. 10.000")
                
                var bayar = 0
                while (true) {
                    print("Mohon masukkan total bayar: ")
                    bayar = readLine()!!.toInt()
                    if (bayar >= 10000) {
                        break // Menghentikan loop jika pembayaran valid
                    } else {
                        println("Pembayaran tidak mencukupi. Mohon masukkan jumlah yang sesuai.")
                    }
                } 
                val kembalian = bayar - 10000
                println("Kembalian anda adalah Rp. $kembalian")
                println("Terima kasih atas kunjungannya")
            } 

            do {
                print("Apakah Anda ingin memesan lagi? (ya/tidak): ")
                lanjutkan = readLine()!!.toLowerCase()
                if (lanjutkan != "ya" && lanjutkan != "tidak") {
                    println("Masukkan tidak valid. Mohon jawab dengan ya atau tidak.")
                }
            } while (lanjutkan != "ya" && lanjutkan != "tidak")
        } while (lanjutkan == "ya")
    }
}