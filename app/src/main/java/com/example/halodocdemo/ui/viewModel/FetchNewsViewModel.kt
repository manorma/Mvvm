package com.example.halodocdemo.ui.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.halodocdemo.data.local.NewsDao
import com.example.halodocdemo.data.model.api.Hit
import com.example.halodocdemo.data.model.api.SearchResponse
import com.example.halodocdemo.data.remote.ApiManager
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class FetchNewsViewModel(val apiManager: ApiManager) : ViewModel() {

    private var loadingVisibility: MutableLiveData<Int>
    private var newsList: MutableLiveData<MutableList<NewsItemViewModel>>
    private var mCompositDisposable: CompositeDisposable
    private var newsDao:NewsDao

    init {
        loadingVisibility = MutableLiveData()
        newsList = MutableLiveData()
        mCompositDisposable = CompositeDisposable()
        loadingVisibility.value = View.GONE

    }

    public fun getLoading():MutableLiveData<Int>{
        return loadingVisibility
    }

    fun fetchNews(query: String?) {
        if(query?.length?.compareTo(3)!! < 0){
            return
        }
        showProgressBar();
        //newsList.value=getViewModelList();

        val call = apiManager.getSearchApi().fetchQuery(query)
        call.enqueue(object : Callback<SearchResponse>{
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                hideProgressBar()
                Log.d("FetchNewsViewModel","error:"+t.message)
            }

            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                Log.d("FetchNewsViewModel","response:"+response.body()?.hits)
                hideProgressBar()
                val hits = response.body()?.hits as List<Hit>
                Log.d("FetchNewsViewModel","response:"+getViewModelList(hits))
                newsList.value = getViewModelList(hits)

            }

        })


    }

    private fun  getViewModelList(): MutableList<NewsItemViewModel>{
        val res :MutableList<NewsItemViewModel> = mutableListOf()
        res.add(NewsItemViewModel("title1","author1","create1","url1"))
        res.add(NewsItemViewModel("title2","author1","create1","url1"))
        res.add(NewsItemViewModel("title3","author1","create1","url1"))
        res.add(NewsItemViewModel("title4","author1","create1","url1"))
        res.add(NewsItemViewModel("title5","author1","create1","url1"))

        return res

    }
    private fun getViewModelList(hits: List<Hit>): MutableList<NewsItemViewModel> {
        val res :MutableList<NewsItemViewModel> = mutableListOf()
        hits.forEach {
            res.add(NewsItemViewModel(it?.title,it.created_at,it?.author,it?.url))
        }
        return res
    }

    private fun showProgressBar() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun hideProgressBar() {
        loadingVisibility.value = View.GONE
    }

    fun getNewsItemsLiveData() :MutableLiveData<MutableList<NewsItemViewModel>> {
        return newsList;
    }

    fun setNewsItemsLiveData(newsList : MutableList<NewsItemViewModel>)  {
        this.newsList.value = newsList
    }



}