package com.bsk.locoff.network

import com.bsk.locoff.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
  @GET("users")
  fun getDataFromAPI(): Call<ArrayList<ApiResponse>>
}