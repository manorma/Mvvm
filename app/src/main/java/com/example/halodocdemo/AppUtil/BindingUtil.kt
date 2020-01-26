package com.example.halodocdemo.AppUtil

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.halodocdemo.ui.viewModel.NewsItemViewModel
import com.example.halodocdemo.ui.view.RecyclerAdapter

class BindingUtil private constructor(){

    companion object {

        @BindingAdapter("adapter")
        @JvmStatic
        fun addNewsItem(recyclerView: RecyclerView, blogs: List<NewsItemViewModel>?) {
            Log.d("BindingUtil","addNewItem :"+blogs?.size)
            val adapter = recyclerView.adapter as RecyclerAdapter?
            if (adapter != null ) {
                adapter!!.clearItems()
                blogs?.let {
                    adapter!!.addItem(blogs!!)
                }
            }
        }
    }


}