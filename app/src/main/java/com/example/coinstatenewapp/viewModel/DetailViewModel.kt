package com.example.coinstatenewapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coinstatenewapp.data.database.CoinDatabase
import com.example.coinstatenewapp.data.repository.DbRepositoryImpl
import com.example.coinstatenewapp.data.repository.ServerRepositoryImpl
import com.example.coinstatenewapp.model.Coin
import com.example.coinstatenewapp.model.CoinsModel
import com.example.coinstatenewapp.utils.ApplicationManager.MAIN
import com.example.coinstatenewapp.utils.PrefsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel(val app: Application) : AndroidViewModel(app) {

    private lateinit var dbRepository: DbRepositoryImpl
    var isFavorite = MutableLiveData<Boolean>()

    var coins: MutableLiveData<List<Coin>> = MutableLiveData()

    fun createDBIfNeeded() {
        val coinDao = CoinDatabase.getInstance(MAIN).getCoinDao()
        dbRepository = DbRepositoryImpl(coinDao)
    }

    fun getDataFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            coins.postValue(dbRepository.getAllFavoriteCoins())
        }
    }

    fun onCoinFavoriteClick(coin: Coin, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            if (!coin.isFavorite) {
                dbRepository.insertCoin(coin) {
                    PrefsManager.putFavorite(MAIN, coin.id, !coin.isFavorite)
                    this@DetailViewModel.isFavorite.postValue(!coin.isFavorite)
                    onSuccess()
                }
            } else {
                dbRepository.deleteCoin(coin) {
                    PrefsManager.putFavorite(MAIN, coin.id, !coin.isFavorite)
                    this@DetailViewModel.isFavorite.postValue(!coin.isFavorite)
                    onSuccess()
                }
            }
        }
    }


}