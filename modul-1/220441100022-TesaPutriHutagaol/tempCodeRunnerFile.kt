        do{
            println("apakah ingin memesan lagi? (ya / tidak) ")
            lanjut = readLine()!!.toLowerCase()
            if(lanjut != "ya" && lanjut != "tidak"){
                println("mohon masukkan yang sesuai dengan perintah!!")
            } 
        }while(lanjut != "ya" && lanjut != "tidak")
    } while (lanjut == "ya")
}