package com.example.halodocdemo.data.model.api.db

import androidx.room.Entity

@Entity(tableName = "news")
data class NewsItem(val title:String,
                    val createAt:String,
                    var author:String,
                    var url:String){

}