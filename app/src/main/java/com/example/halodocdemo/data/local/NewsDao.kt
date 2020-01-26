package com.example.halodocdemo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.halodocdemo.data.model.api.db.NewsItem


@Dao
public interface NewsDao {

    @androidx.room.Query("SELECT * FROM news")
    fun loadAll(): List<NewsItem>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: NewsItem)
}