package com.example.halodocdemo.ui.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.halodocdemo.R
import com.example.halodocdemo.ViewModelProviderFactory
import com.example.halodocdemo.data.remote.ApiManager
import com.example.halodocdemo.databinding.ActivityMainBinding
import com.example.halodocdemo.ui.viewModel.FetchNewsViewModel

class MainActivity : AppCompatActivity(), RecyclerAdapter.ItemClickLisener {
    override fun OnItemClick(url: String) {
        val intent = Intent(this, NewsDetailActivty::class.java)
        intent.putExtra(NewsDetailActivty.ARG_URL, url)
        startActivity(intent)

    }


    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FetchNewsViewModel
    private lateinit var myAdapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ViewModelProviderFactory(ApiManager(applicationContext))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, factory).get(FetchNewsViewModel::class.java)
        binding.viewModel = viewModel
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        myAdapter = RecyclerAdapter()
        myAdapter.setLisener(this)
        binding.recyclerView.adapter = myAdapter
        binding.searchView.queryHint = "Search news"
        binding.searchView.isIconified = false
        viewModel.getLoading().value = View.GONE
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.fetchNews(newText)
                return false
            }

        })


        subscribeToLiveData()


    }

    fun subscribeToLiveData() {
        viewModel.getLoading().value = View.GONE
        viewModel.getNewsItemsLiveData().observe(this, Observer {
            myAdapter.clearItems()
            myAdapter.addItem(it)
        })

    }


}
