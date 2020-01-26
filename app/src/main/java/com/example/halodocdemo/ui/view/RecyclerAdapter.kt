package com.example.halodocdemo.ui.view

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.halodocdemo.databinding.ItemViewBinding
import com.example.halodocdemo.ui.viewModel.NewsItemViewModel


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private lateinit var clickLisener: ItemClickLisener
    private var newsList: MutableList<NewsItemViewModel>

    init {
        newsList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater, parent, false) as ItemViewBinding
        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int {
        Log.d("Adapter", "itemCount :" + newsList.size)
        return newsList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)

    }

    fun setLisener(lisener: ItemClickLisener) {
        this.clickLisener = lisener
    }

    interface ItemClickLisener {
        fun OnItemClick(url: String);
    }

    fun addItem(newsList: List<NewsItemViewModel>) {
        Log.d("Adapter", "addItems :" + newsList)
        this.newsList.addAll(newsList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.newsList.clear()
    }

    inner class MyViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        override fun onClick(v: View?) {
            Log.d("MyViewHolder", "onClick:")
            //clickLisener.OnItemClick(adapterPosition)
            val intent = Intent(binding.root.context, NewsDetailActivty::class.java)
            val url :String = newsList?.get(adapterPosition)?.url.toString()
            url?.let {
                intent.putExtra("arg_url",url);
            }
            binding.root.context.startActivity(intent)
        }


    fun bind(pos: Int) {
        val newsItemViewModel = newsList.get(pos) as NewsItemViewModel
        Log.d("Adapter", "newsItem :" + newsItemViewModel.author)
        binding.viewModel = newsItemViewModel
        val url :String? = newsList?.get(pos)?.url?.value
        binding.root.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d("MyViewHolder", "onClick:url" +url)
                clickLisener.OnItemClick(url!!)
            }

        })
        binding.executePendingBindings()


    }

}


}