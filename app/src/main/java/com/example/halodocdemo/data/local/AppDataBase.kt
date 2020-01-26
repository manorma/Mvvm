package com.example.halodocdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.halodocdemo.data.model.api.db.NewsItem
import java.util.*

@Database(entities = arrayOf(NewsItem::class), version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun newsDao():NewsDao

}
