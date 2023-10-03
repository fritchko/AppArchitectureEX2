package com.example.networktest1.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networktest1.model.Fact
import com.example.networktest1.network.FactProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FactsViewModel(private val provider: FactProvider) : ViewModel() {

    private var _result = MutableLiveData<Fact?>()

    val result: LiveData<Fact?>
        get() = _result

    private var _isLoading = MutableLiveData(false)

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getFacts() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val response = provider.getFacts(randomNumber())
            if (response != null) {
                _isLoading.postValue(false)
                _result.postValue(response)
                response.data?.forEachIndexed { index, dataLocal ->
                    Log.i("DATA", "$index, $dataLocal")
                }
            } else {
                Log.e("NETWORK", "Couldn't achieve network call.")
            }
        }
    }

    private fun randomNumber(): Int {
        var currentPage = (0..20).random()
        return currentPage
    }


}