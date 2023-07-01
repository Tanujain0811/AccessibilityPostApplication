package com.example.apitest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitest.models.DataList
import com.example.apitest.models.DataListItem
import com.example.apitest.repo.DataRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(private val repo: DataRepo):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getData()
        }
    }
    val data : LiveData<DataList>
        get()  =  repo.data
    val dbData : LiveData<List<DataListItem>>
        get()  =  repo.dbData


}
