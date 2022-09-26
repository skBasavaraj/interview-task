package com.bsk.locoff.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

@GET("users")
  fun getDataFromAPI(): Call<ArrayList<ApiResponse>>
}