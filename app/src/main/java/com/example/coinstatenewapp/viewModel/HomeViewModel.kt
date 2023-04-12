package com.example.coinstatenewapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coinstatenewapp.data.repository.CoinRepositoryImpl
import com.example.coinstatenewapp.model.CoinsModel
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(val app: Application) : AndroidViewModel(app) {

    private val repository = CoinRepositoryImpl()
    var coins: MutableLiveData<Response<CoinsModel>> = MutableLiveData()

    fun getCoinsFromServer() {
        viewModelScope.launch {
            coins.value = repository.getAllCoinsFromServer()
        }
    }


}