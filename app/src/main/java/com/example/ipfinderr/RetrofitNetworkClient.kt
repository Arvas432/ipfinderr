package com.example.ipfinderr
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworkClient: NetworkClient {
    private val ipFindBaseUrl = "https://api.ipgeolocation.io/ipgeo/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(ipFindBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val iTunesService = retrofit.create(IpFindApi::class.java)
    override fun doRequest(dto: Any): Response {
        if(dto is IPFindRequest){
            Log.i("NETWORK", "IM HERE")
            Log.i("NETWORK", "IM HERE")
            Log.i("NETWORK", "IM HERE")
            val resp = iTunesService.search(dto.expression).execute()
            if(resp.body()==null){
                Log.i("NETWORK", "null")
            }
            val body = resp.body() ?: Response()
            Log.i("NETWORK", resp.code().toString())
            return body.apply { resultCode = resp.code() }
        }else{
            return Response().apply { resultCode=400 }
        }
    }

}