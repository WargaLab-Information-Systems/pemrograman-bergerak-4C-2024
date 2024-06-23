package com.example.praktikum06.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.praktikum06.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel() {
    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> = _players

    init {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        val client = ApiConfig.getApiService().getPlayers()
        client.enqueue(object : Callback<PlayerResponse> {
            override fun onResponse(call: Call<PlayerResponse>, response: Response<PlayerResponse>) {
                if (response.isSuccessful) {
                    _players.value = response.body()?.data
                }
            }

            override fun onFailure(call: Call<PlayerResponse>, t: Throwable) {
                // Handle error
            }
        })
    }
}
