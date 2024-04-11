package com.example.ipfinderr

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IpFindApi {
    @GET("?apiKey=455322ed084f4554b56ceebebcf907ae")
    fun search(@Query("ip") ip: String): Call<IPFindResponse>
}