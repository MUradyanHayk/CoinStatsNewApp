package com.example.coinstatenewapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.coinstatenewapp.data.database.CoinDatabase
import com.example.coinstatenewapp.data.repository.DbRepositoryImpl
import com.example.coinstatenewapp.data.repository.ServerRepositoryImpl
import com.example.coinstatenewapp.model.Coin
import com.example.coinstatenewapp.model.CoinsModel
import com.example.coinstatenewapp.utils.ApplicationManager.MAIN
import com.example.coinstatenewapp.utils.PrefsManager
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(val app: Application) : AndroidViewModel(app) {

    private val serverRepository = ServerRepositoryImpl()
    private lateinit var dbRepository: DbRepositoryImpl
    var isFavorite = MutableLiveData<Boolean>()

    var coins: MutableLiveData<Response<CoinsModel>> = MutableLiveData()

    fun getCoinsFromServer() {
        viewModelScope.launch {
            coins.value = serverRepository.getAllCoinsFromServer()
        }
    }

    fun getList(): LiveData<PagingData<Coin>> {
        return serverRepository.getCoins().cachedIn(viewModelScope)
    }

    fun createDBIfNeeded() {
        val coinDao = CoinDatabase.getInstance(MAIN).getCoinDao()
        dbRepository = DbRepositoryImpl(coinDao)
    }

    fun onCoinFavoriteClick(coin: Coin, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            if (!coin.isFavorite) {
                dbRepository.insertCoin(coin) {
                    PrefsManager.putFavorite(MAIN, coin.id, !coin.isFavorite)
                    this@HomeViewModel.isFavorite.postValue(!coin.isFavorite)
                    onSuccess()
                }
            } else {
                dbRepository.deleteCoin(coin) {
                    PrefsManager.putFavorite(MAIN, coin.id, !coin.isFavorite)
                    this@HomeViewModel.isFavorite.postValue(!coin.isFavorite)
                    onSuccess()
                }
            }
        }
    }


}