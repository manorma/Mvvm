package com.example.halodocdemo.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.halodocdemo.R
import com.example.halodocdemo.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.view.*

class NewsDetailActivty : AppCompatActivity() {

    companion object{
        const val ARG_URL :String = "arg_url"
        val TAG :String = NewsDetailActivty::class.java.simpleName
    }

    lateinit var binding:ActivityDetailBinding
    var url:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        if(intent != null){
            url = intent?.extras?.getString(ARG_URL)
        }
        url?.let {
            Log.d("NewsDatilActivity","webViewload")
            binding.webView.loadUrl(url)
        }


    }

}