package com.assesment.unifynd.data.repository

import android.content.Context
import com.assesment.unifynd.utils.SafeApiCall
import javax.inject.Inject

class HomeRepository @Inject constructor() : SafeApiCall {

    suspend fun getJsonDataFromAsset(context: Context, fileName: String) = safeApiCall {

        context.assets.open(fileName).bufferedReader().use { it.readText() }

    }

}