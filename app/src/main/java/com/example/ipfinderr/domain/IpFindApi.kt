package com.example.ipfinderr.domain

import com.example.ipfinderr.data.IPFindResponse
import com.example.ipfinderr.data.dto.IpFindDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IpFindApi {
    @GET("/ipgeo?apiKey=455322ed084f4554b56ceebebcf907ae")
    fun search(@Query("ip") text : String): Call<IpFindDto>
}