package com.avrzll.implementasi6.data.remote

//untuk merepresentasikan struktur umum dari respon API yang diterima dari server
data class APIResponse( //Data class digunakan untuk menyimpan data
    val error: Boolean,
    val message: String,
    val count: Int,
    val data: List<PlayerData>
)
