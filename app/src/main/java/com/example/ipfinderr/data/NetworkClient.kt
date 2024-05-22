package com.example.ipfinderr.data

import com.example.ipfinderr.data.dto.Response

interface NetworkClient{
    fun doRequest(dto: Any): Response
}
