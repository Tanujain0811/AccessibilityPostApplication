package com.example.apitest.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apitest.api.DataService
import com.example.apitest.db.PostsDatabase
import com.example.apitest.models.DataList
import com.example.apitest.models.DataListItem
import com.example.apitest.utils.NetworkUtils


class DataRepo(
    private val dataService: DataService,
    private val postsDatabase: PostsDatabase,
    private val applicationContext: Context
) {

    private val dataLiveData = MutableLiveData<DataList>()
    val data: LiveData<DataList>
        get() = dataLiveData
    private val dbLiveData = MutableLiveData<List<DataListItem>>()
    val dbData: LiveData<List<DataListItem>>
        get() = dbLiveData


    suspend fun getData() {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = dataService.getData()
            if (result?.body() != null) {
                for (post in result?.body()!!) {
                    if (!postsDatabase.postDao().isIdExists(post.id)) {
                        postsDatabase.postDao().insertPosts(post)
                    }
                }
                dataLiveData.postValue(result.body())
            }
        } else {
            val posts = postsDatabase.postDao().getPosts()
            dbLiveData.postValue(posts)
        }

    }
}