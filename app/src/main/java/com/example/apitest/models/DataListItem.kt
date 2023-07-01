package com.example.apitest.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class DataListItem(
    val body: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val userId: Int
)