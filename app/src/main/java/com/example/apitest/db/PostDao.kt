package com.example.apitest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.apitest.models.DataList
import com.example.apitest.models.DataListItem


@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    suspend fun getPosts(): List<DataListItem>

    @Insert
    suspend fun insertPosts(post: DataListItem)

    @Query("SELECT EXISTS(SELECT 1 FROM posts WHERE id = :id LIMIT 1)")
    fun isIdExists(id: Int): Boolean
}

