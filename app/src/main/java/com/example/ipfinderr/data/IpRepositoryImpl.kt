package com.example.ipfinderr.data

import android.util.Log
import com.example.ipfinderr.data.dto.IPFindRequest
import com.example.ipfinderr.data.dto.IPFindResponse
import com.example.ipfinderr.domain.search.IpResult
import com.example.ipfinderr.domain.search.IpRepository

class IpRepositoryImpl(private val networkClient: NetworkClient): IpRepository {
    override fun searchIp(expression: String): IpResult {
        val response = networkClient.doRequest(IPFindRequest(expression))
        Log.i("NETWORK", response.resultCode.toString())
        if(response.resultCode == 200){
            Log.i("REPOSITORY", "IM HERE")
            val resp = (response as IPFindResponse)

            if(resp == null){
                Log.i("REPOSITORY", "NULL")
            }
            Log.i("REPOSITORY", resp.resultCode.toString())
            return IpResult(
                resp.result.ip ?:"",
                resp.result.countryName?:"",
                resp.result.city?:"",
                resp.result.isp?:"",
                resp.result.latitude?:"",
                resp.result.longitude?:"",
                resp.result.countryCode2?:"",
                resp.result.stateProv?:"",
                resp.result.zipcode?: "",
                "https://flagsapi.com/${resp.result.countryCode2?:""}/flat/64.png",
                resp.result.timeZone?.name?: "",
                resp.result.currency?.name?: ""
            )
        } else{
            Log.i("Repository", response.resultCode.toString())
            return IpResult("", "", "", "", "", "", "", "", "", "", "", "")
        }
    }
}