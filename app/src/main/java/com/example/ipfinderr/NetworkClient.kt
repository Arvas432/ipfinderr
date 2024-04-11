package com.example.ipfinderr
import com.example.ipfinderr.Response
interface NetworkClient{
    fun doRequest(dto: Any): Response
}
