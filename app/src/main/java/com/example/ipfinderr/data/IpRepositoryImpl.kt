package com.example.ipfinderr.data

import android.util.Log
import com.example.ipfinderr.domain.IpResult
import com.example.ipfinderr.data.dto.IpFindDto
import com.example.ipfinderr.domain.IpRepository

class IpRepositoryImpl(private val networkClient: NetworkClient): IpRepository {
    override fun searchIp(expression: String): IpResult {
        val response = networkClient.doRequest(IPFindRequest(expression))
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
                resp.result.district?:"",
                resp.result.zipcode?: "",
                resp.result.countryFlag?:""
            )
        } else{
            Log.i("Repository", response.resultCode.toString())
            return IpResult("", "", "", "", "", "", "", "", "", "")
        }
    }
}