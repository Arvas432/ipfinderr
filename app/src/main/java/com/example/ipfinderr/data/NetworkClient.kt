package com.example.ipfinderr.data

interface NetworkClient{
    fun doRequest(dto: Any): Response
}
