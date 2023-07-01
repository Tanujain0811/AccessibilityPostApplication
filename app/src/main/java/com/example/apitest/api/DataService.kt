package com.example.apitest.api

import com.example.apitest.models.DataList
import com.example.apitest.models.DataListItem
import retrofit2.Response
import retrofit2.http.GET

interface DataService {

    @GET("posts")
    suspend fun getData(): Response<DataList>

}