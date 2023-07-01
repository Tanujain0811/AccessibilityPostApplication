package com.example.apitest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apitest.models.DataListItem

@Database(entities = [DataListItem::class], version = 2)

abstract class PostsDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        @Volatile
        private var instance: PostsDatabase? = null

        fun getDatabase(context: Context): PostsDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context, PostsDatabase::class.java, "postDb")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }
    }

}