package com.example.halodocdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.halodocdemo.data.remote.ApiManager
import com.example.halodocdemo.ui.viewModel.FetchNewsViewModel

class ViewModelProviderFactory(val apiManager: ApiManager) :ViewModelProvider.NewInstanceFactory(){


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FetchNewsViewModel::class.java)){
            return FetchNewsViewModel(apiManager) as T
        }
        return super.create(modelClass)
    }
}