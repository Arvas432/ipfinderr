package com.example.ipfinderr

import android.util.Log

class IpRepositoryImpl(private val networkClient: NetworkClient): IpRepository {
    override fun searchIp(expression: String): IpResult {
        val response = networkClient.doRequest(IPFindRequest(expression))
        if(response.resultCode == 200){
            Log.i("REPOSITORY", "IM HERE")
            val resp = (response as IPFindResponse).body
            if(resp == null){
                Log.i("REPOSITORY", "NULL")
            }
            return IpResult(
                resp.ip ?:"",
                resp.country_name?:"",
                resp.city?:"",
                resp.isp?:"",
                resp.latitude?:"",
                resp.longitude?:"",
                resp.country_code2?:"",
                resp.district?:"",
                resp.zipcode?: "",
                resp.timezone ?: emptyList(),
                resp.currency?: emptyList()
            )
        } else{
            return IpResult("", "", "", "", "", "", "", "", "", emptyList(), emptyList())
        }
    }
}