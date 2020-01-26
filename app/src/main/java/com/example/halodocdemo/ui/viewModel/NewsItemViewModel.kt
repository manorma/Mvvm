package com.example.halodocdemo.ui.viewModel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData

class NewsItemViewModel(title: String?, create: String?, author: String?, url: String?) {
    public var title = MutableLiveData<String>()
    public var create = MutableLiveData<String>()
    public var author = MutableLiveData<String>()
    public var url = MutableLiveData<String>()

    init {
        Log.d("NewsItemViewModel", "init")
        this.title.value = title
        this.create.value = create
        this.author.value = author
        this.url.value = url


    }






}