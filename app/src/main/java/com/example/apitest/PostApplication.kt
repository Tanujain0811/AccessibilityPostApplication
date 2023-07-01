package com.example.apitest

import android.app.Application
import com.example.apitest.api.RetrofitHelper
import com.example.apitest.db.PostsDatabase
import com.example.apitest.repo.DataRepo

class PostApplication : Application() {

    lateinit var repo: DataRepo
    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        val dataService = RetrofitHelper.instance
        val database = PostsDatabase.getDatabase(applicationContext)
        repo = DataRepo(dataService, database, applicationContext)
    }
}