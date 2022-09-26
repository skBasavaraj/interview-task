package com.bsk.locoff

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bsk.locoff.model.ApiResponse
import com.bsk.locoff.model.RetrofitInstance
import com.bsk.locoff.model.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel:ViewModel() {
    var mutableLiveData:MutableLiveData<List<ApiResponse>>
    init {
        mutableLiveData = MutableLiveData()
    }
     fun getViewModel():MutableLiveData<List<ApiResponse>>{
       return  mutableLiveData
    }
    /**
     *   request api
     */
    fun makeApiCall(){
        val retrofitService =  RetrofitInstance.getInstance().create(RetrofitService::class.java)
        val data = retrofitService.getDataFromAPI()
        data.enqueue(object : Callback<ArrayList<ApiResponse>?> {
            override fun onResponse(
                call: Call<ArrayList<ApiResponse>?>,
                response: Response<ArrayList<ApiResponse>?>
            ) {
                val response = response.body()
                mutableLiveData.postValue(response)
             }

            override fun onFailure(call: Call<ArrayList<ApiResponse>?>, t: Throwable) {
                mutableLiveData.postValue(null)
            }
        })
    }
}