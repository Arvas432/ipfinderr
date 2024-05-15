package com.example.ipfinderr.data.localstorage

import com.example.ipfinderr.domain.IpResult

interface LocalIpStorageHandler {
    fun write(input: IpResult)
    fun clear()
    fun read(): List<IpResult>
}