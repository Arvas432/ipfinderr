package com.example.ipfinderr.data
import android.util.Log
import com.example.ipfinderr.data.dto.IPFindRequest
import com.example.ipfinderr.data.dto.IPFindResponse
import com.example.ipfinderr.data.dto.Response
import com.example.ipfinderr.domain.search.IpFindApi

class RetrofitNetworkClient(private val ipFindService: IpFindApi): NetworkClient {

    override fun doRequest(dto: Any): Response {
        if(dto is IPFindRequest){
            val resp = ipFindService.search(dto.expression).execute()
            Log.i("NETWORK", resp.body().toString())
            val body = resp.body() ?: Response()
            if (resp.body()!=null){
                return IPFindResponse(resp.body()!!).apply { resultCode=resp.code() }
            }else{
                return Response().apply { resultCode=400 }
            }
        }else{
            return Response().apply { resultCode=400 }
        }
    }

}