package com.example.networktest1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.networktest1.network.FactProvider
import com.example.networktest1.ui.FactsViewModel

class MainViewModelFactory(private val provider: FactProvider): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FactsViewModel::class.java)){
            return FactsViewModel(provider) as T
        } else{
            throw Exception("ViewModel not found.")
        }
    }

}