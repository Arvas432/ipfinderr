package com.example.ipfinderr.domain


interface SearchHistoryInteractor {
    fun write(input: IpResult)
    fun clear()
    fun read(): List<IpResult>
}