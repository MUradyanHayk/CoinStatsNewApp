package com.example.coinstatenewapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coinstatenewapp.data.database.CoinDatabase
import com.example.coinstatenewapp.data.repository.DbRepositoryImpl
import com.example.coinstatenewapp.data.repository.ServerRepositoryImpl
import com.example.coinstatenewapp.model.CoinsModel
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(val app: Application) : AndroidViewModel(app) {

    private val serverRepository = ServerRepositoryImpl()
    private lateinit var dbRepository: DbRepositoryImpl

    var coins: MutableLiveData<Response<CoinsModel>> = MutableLiveData()

    fun getCoinsFromServer() {
        viewModelScope.launch {
            coins.value = serverRepository.getAllCoinsFromServer()
        }
    }

    fun createDB() {
        val coinDao = CoinDatabase.getInstance(app).getCoinDao()
        dbRepository = DbRepositoryImpl(coinDao)
    }


}