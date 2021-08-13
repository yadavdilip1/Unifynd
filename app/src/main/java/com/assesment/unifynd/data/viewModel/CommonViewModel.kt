package com.assesment.unifynd.data.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assesment.unifynd.data.model.ModelData
import com.assesment.unifynd.data.repository.HomeRepository
import com.assesment.unifynd.utils.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val context: Context
) : ViewModel() {

    private val _homeListItemsLiveData = MutableLiveData<Resource<List<ModelData>>>()
    val homeListItemsLiveData: LiveData<Resource<List<ModelData>>>
        get() = _homeListItemsLiveData

    init {
        getHomeListItems()
    }

    private fun getHomeListItems() = viewModelScope.launch {

        _homeListItemsLiveData.postValue(Resource.Loading)

        val modelData = repository.getJsonDataFromAsset(context, "JsonData.json")

        if (modelData is Resource.Success) {

            val gson = Gson()
            val listDataType = object : TypeToken<List<ModelData>>() {}.type
            val data: List<ModelData> = gson.fromJson(modelData.value, listDataType)

            _homeListItemsLiveData.postValue(Resource.Success(data))

        } else {
            Resource.Failure("Something went wrong")
        }
    }

}